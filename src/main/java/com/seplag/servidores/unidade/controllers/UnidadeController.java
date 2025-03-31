package com.seplag.servidores.unidade.controllers;

import com.seplag.servidores.compartilhado.dtos.response.RecursoIdResponse;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.unidade.dtos.requests.NovaUnidadeRequest;
import com.seplag.servidores.unidade.dtos.requests.UnidadeUpdateRequest;
import com.seplag.servidores.unidade.dtos.responses.UnidadeResponse;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.services.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService unidadeService;
    private final ModelMapper modelMapper;

    @PostMapping("/api/v1/unidades")
    public ResponseEntity<RecursoIdResponse> criarUnidade(@Valid @RequestBody NovaUnidadeRequest request) {
        Long id = unidadeService.criar(request);
        return ResponseEntity.ok(new RecursoIdResponse(id));
    }

    @GetMapping("/api/v1/unidades")
    public ResponseEntity<Page<UnidadeResponse>> buscarTodas(Pageable pageable) {
        Page<UnidadeResponse> unidades = unidadeService
                .buscarTodas(pageable)
                .map(u -> modelMapper.map(u, UnidadeResponse.class));

        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/api/v1/unidades/{id}")
    public ResponseEntity<UnidadeResponse> buscarPorId(@PathVariable Long id) {
        Unidade unidade = unidadeService
                .buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada com o id %d".formatted(id)));

        return ResponseEntity.ok(modelMapper.map(unidade, UnidadeResponse.class));
    }

    @PutMapping("/api/v1/unidades/{id}")
    public ResponseEntity<Void> atualizarUnidade(@PathVariable Long id, @Valid @RequestBody UnidadeUpdateRequest request) {
        unidadeService.atualizarPorId(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/unidades/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
