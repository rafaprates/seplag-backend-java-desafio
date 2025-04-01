package com.seplag.servidores.unidade.controllers;

import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.unidade.dtos.requests.CriarUnidadeDTO;
import com.seplag.servidores.unidade.dtos.requests.UnidadeUpdateRequest;
import com.seplag.servidores.unidade.dtos.responses.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.unidade.dtos.responses.UnidadeResponseDTO;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.mapper.UnidadeMapper;
import com.seplag.servidores.unidade.services.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;
    private final UnidadeMapper unidadeMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> criarUnidade(@Valid @RequestBody CriarUnidadeDTO request) {
        Long id = unidadeService.criar(unidadeMapper.toEntity(request));
        return ResponseEntity.ok(new RecursoCriadoDTO(id));
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeResponseDTO>> buscarTodas(Pageable pageable) {
        Page<UnidadeResponseDTO> unidades = unidadeService
                .buscarTodas(pageable)
                .map(unidadeMapper::toDTO);

        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{id}/servidores-efetivos")
    public ResponseEntity<List<ServidorEfetivoUnidadeResponseDTO>> buscarServidoresEfetivos(@PathVariable Long id, Pageable pageable) {
        List<ServidorEfetivoUnidadeResponseDTO> response = unidadeService.buscarServidoresEfetivosPorUnidadeId(id, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseDTO> buscarPorId(@PathVariable Long id) {
        Unidade unidade = unidadeService
                .buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada com o id %d".formatted(id)));

        return ResponseEntity.ok(unidadeMapper.toDTO(unidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUnidade(@PathVariable Long id, @Valid @RequestBody UnidadeUpdateRequest request) {
        unidadeService.atualizarPorId(id, unidadeMapper.toEntity(request));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
