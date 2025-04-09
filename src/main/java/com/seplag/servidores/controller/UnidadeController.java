package com.seplag.servidores.controller;

import com.seplag.servidores.dto.response.RecursoCriadoDTO;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.dto.request.CriarUnidadeDTO;
import com.seplag.servidores.dto.request.UnidadeUpdateRequest;
import com.seplag.servidores.dto.response.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.dto.response.UnidadeResponseDTO;
import com.seplag.servidores.entity.Unidade;
import com.seplag.servidores.mapper.UnidadeMapper;
import com.seplag.servidores.service.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/unidades")
@Tag(name = "Unidades", description = "CRUD completo para Unidades")
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

    @Operation(summary = "Consulta servidores efetivos por unidade")
    @GetMapping("/{id}/servidores-efetivos")
    public ResponseEntity<Page<ServidorEfetivoUnidadeResponseDTO>> buscarServidoresEfetivos(@PathVariable Long id, Pageable pageable) {
        Page<ServidorEfetivoUnidadeResponseDTO> response = unidadeService.buscarServidoresEfetivosPorUnidadeId(id, pageable);
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
