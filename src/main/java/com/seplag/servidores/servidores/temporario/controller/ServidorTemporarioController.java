package com.seplag.servidores.servidores.temporario.controller;

import com.seplag.servidores.compartilhado.dtos.response.FotoResponseDTO;
import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.entities.Foto;
import com.seplag.servidores.compartilhado.entities.Pessoa;
import com.seplag.servidores.compartilhado.mappers.FotoMapper;
import com.seplag.servidores.compartilhado.services.PessoaService;
import com.seplag.servidores.servidores.temporario.dtos.request.AtualizarServidorTemporarioDTO;
import com.seplag.servidores.servidores.temporario.dtos.request.CriarServidorTemporarioDTO;
import com.seplag.servidores.servidores.temporario.dtos.response.ServidorTemporarioResponseDTO;
import com.seplag.servidores.servidores.temporario.entities.ServidorTemporario;
import com.seplag.servidores.servidores.temporario.services.ServidorTemporarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;
    private final PessoaService pessoaService;
    private final FotoMapper fotoMapper;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> registrarServidorTemporario(@RequestBody CriarServidorTemporarioDTO request) {
        Set<Endereco> enderecoEntities = request
                .pessoa()
                .enderecos()
                .stream()
                .map(e -> new Endereco(
                        e.tipoLogradouro(),
                        e.logradouro(),
                        e.numero(),
                        e.bairro(),
                        new Cidade(e.cidadeId())
                ))
                .collect(Collectors.toSet());

        Pessoa pessoaEntity = new Pessoa(
                request.pessoa().nome(),
                request.pessoa().dataNascimento(),
                request.pessoa().sexo(),
                request.pessoa().mae(),
                request.pessoa().pai(),
                enderecoEntities
        );

        ServidorTemporario servidorTemporarioEntity = new ServidorTemporario(
                pessoaEntity,
                request.dataAdmissao(),
                request.dataDemissao()
        );

        long id = servidorTemporarioService.registrarServidorTemporario(servidorTemporarioEntity).getId();

        return ResponseEntity.ok(new RecursoCriadoDTO(id));
    }

    @PostMapping("{id}/fotos")
    public ResponseEntity<FotoResponseDTO> adicionarFoto(@PathVariable Long id, @RequestParam MultipartFile foto) {
        Foto ft = pessoaService.adicionarFoto(id, foto);
        return ResponseEntity.ok(fotoMapper.toDTO(ft));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorTemporarioResponseDTO>> buscarTodos(Pageable pageable) {
        Page<ServidorTemporarioResponseDTO> response = servidorTemporarioService
                .buscarTodos(pageable)
                .map(st -> modelMapper.map(st, ServidorTemporarioResponseDTO.class));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> buscarPorId(@PathVariable Long id) {
        ServidorTemporario servidorTemporario = servidorTemporarioService.buscarPorId(id);
        ServidorTemporarioResponseDTO response = modelMapper.map(servidorTemporario, ServidorTemporarioResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> atualizarServidorTemporario(
            @PathVariable Long id,
            @RequestBody AtualizarServidorTemporarioDTO request
    ) {
        Set<Endereco> enderecoEntities = request
                .pessoa()
                .enderecos()
                .stream()
                .map(e -> new Endereco(
                        e.id(),
                        e.tipoLogradouro(),
                        e.logradouro(),
                        e.numero(),
                        e.bairro(),
                        new Cidade(e.cidadeId())
                ))
                .collect(Collectors.toSet());

        Pessoa pessoaEntity = new Pessoa(
                request.pessoa().nome(),
                request.pessoa().dataNascimento(),
                request.pessoa().sexo(),
                request.pessoa().mae(),
                request.pessoa().pai(),
                enderecoEntities
        );

        ServidorTemporario servidorTemporarioEntity = new ServidorTemporario(
                pessoaEntity,
                request.dataAdmissao(),
                request.dataDemissao()
        );

        servidorTemporarioService.atualizarPorId(id, servidorTemporarioEntity);

        ServidorTemporarioResponseDTO response = modelMapper.map(servidorTemporarioEntity, ServidorTemporarioResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServidorTemporario(@PathVariable Long id) {
        servidorTemporarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
