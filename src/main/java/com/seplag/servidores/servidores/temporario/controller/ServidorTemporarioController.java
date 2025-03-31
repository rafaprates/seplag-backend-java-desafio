package com.seplag.servidores.servidores.temporario.controller;

import com.seplag.servidores.compartilhado.dtos.response.RecursoIdResponse;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.entities.Pessoa;
import com.seplag.servidores.servidores.temporario.dtos.CriarServidorTemporarioDTO;
import com.seplag.servidores.servidores.temporario.dtos.ServidorTemporarioResponse;
import com.seplag.servidores.servidores.temporario.entities.ServidorTemporario;
import com.seplag.servidores.servidores.temporario.services.ServidorTemporarioService;
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
@RequestMapping("/api/v1/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<RecursoIdResponse> registrarServidorTemporario(@RequestBody CriarServidorTemporarioDTO request) {
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

        return ResponseEntity.ok(new RecursoIdResponse(id));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorTemporarioResponse>> buscarTodos(Pageable pageable) {
        Page<ServidorTemporarioResponse> response = servidorTemporarioService
                .buscarTodos(pageable)
                .map(st -> modelMapper.map(st, ServidorTemporarioResponse.class));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponse> buscarPorId(@PathVariable Long id) {
        ServidorTemporario servidorTemporario = servidorTemporarioService.buscarPorId(id);
        ServidorTemporarioResponse response = modelMapper.map(servidorTemporario, ServidorTemporarioResponse.class);
        return ResponseEntity.ok(response);
    }
}
