package com.seplag.servidores.servidores.efetivo.controller;

import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.entities.Pessoa;
import com.seplag.servidores.servidores.efetivo.dtos.request.AtualizarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.request.CriarServidorEfetivoDTO;
import com.seplag.servidores.servidores.efetivo.dtos.response.ServidorEfetivoResponseDTO;
import com.seplag.servidores.servidores.efetivo.entities.ServidorEfetivo;
import com.seplag.servidores.servidores.efetivo.services.ServidorEfetivoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> registrarServidorEfetivo(@RequestBody CriarServidorEfetivoDTO request) {
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

        ServidorEfetivo servidorEfetivoEntity = new ServidorEfetivo(
                request.matricula(),
                pessoaEntity
        );

        long id = servidorEfetivoService.registrarServidorEfetivo(servidorEfetivoEntity).getId();

        return ResponseEntity.ok(new RecursoCriadoDTO(id));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorEfetivoResponseDTO>> buscarTodos(Pageable pageable) {
        Page<ServidorEfetivoResponseDTO> response = servidorEfetivoService
                .buscarTodos(pageable)
                .map(st -> modelMapper.map(st, ServidorEfetivoResponseDTO.class));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> buscarPorId(@PathVariable Long id) {
        ServidorEfetivo servidorTemporario = servidorEfetivoService.buscarPorId(id);
        ServidorEfetivoResponseDTO response = modelMapper.map(servidorTemporario, ServidorEfetivoResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> atualizarServidorEfetivo(
            @PathVariable Long id,
            @RequestBody AtualizarServidorEfetivoDTO request
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

        ServidorEfetivo servidorTemporarioEntity = new ServidorEfetivo(
                request.matricula(),
                pessoaEntity
        );

        servidorEfetivoService.atualizarPorId(id, servidorTemporarioEntity);

        ServidorEfetivoResponseDTO response = modelMapper.map(servidorTemporarioEntity, ServidorEfetivoResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServidorEfetivo(@PathVariable Long id) {
        servidorEfetivoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
