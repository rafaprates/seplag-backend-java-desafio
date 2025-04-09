package com.seplag.servidores.controller;

import com.seplag.servidores.dto.request.AtualizarLotacaoDTO;
import com.seplag.servidores.dto.request.CriarLotacaoDTO;
import com.seplag.servidores.dto.response.EnderecoResponseDTO;
import com.seplag.servidores.dto.response.LotacaoResponseDTO;
import com.seplag.servidores.dto.response.RecursoCriadoDTO;
import com.seplag.servidores.entity.Lotacao;
import com.seplag.servidores.mapper.EnderecoMapper;
import com.seplag.servidores.mapper.LotacaoMapper;
import com.seplag.servidores.service.LotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lotacoes")
@Tag(name = "Lotação", description = "CRUD completo para Lotação")
public class LotacaoController {

    private final LotacaoService lotacaoService;
    private final LotacaoMapper lotacaoMapper;
    private final EnderecoMapper enderecoMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> criar(@Valid @RequestBody CriarLotacaoDTO request) {
        Lotacao nova = lotacaoService.criar(lotacaoMapper.toEntity(request));
        return ResponseEntity.ok(new RecursoCriadoDTO(nova.getId()));
    }

    @GetMapping
    public ResponseEntity<List<LotacaoResponseDTO>> listarTodas() {
        List<LotacaoResponseDTO> lotacoes = lotacaoService.listarTodas()
                .stream()
                .map(lotacaoMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lotacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        Lotacao lotacao = lotacaoService.buscarPorId(id);
        return ResponseEntity.ok(lotacaoMapper.toDTO(lotacao));
    }

    @GetMapping("/por-parte-nome-servidor")
    @Operation(summary = "Consulta endereço funcional a partir de parte do nome do Servidor Efetivo")
    public ResponseEntity<Page<EnderecoResponseDTO>> filtrarPorNomeServidor(@RequestParam String parteNomeServidor, Pageable pageable) {
        Page<Lotacao> lotacoesPage = lotacaoService.filtrarPorNomeServidor(parteNomeServidor, pageable);

        List<EnderecoResponseDTO> enderecos = lotacoesPage
                .stream()
                .map(Lotacao::getUnidade)
                .flatMap(u -> u.getEnderecos().stream())
                .map(enderecoMapper::toDTO)
                .toList();

        Page<EnderecoResponseDTO> enderecosPage = new PageImpl<>(
                enderecos,
                pageable,
                lotacoesPage.getTotalElements()
        );

        return ResponseEntity.ok(enderecosPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> atualizarPorId(@PathVariable Long id, @Valid @RequestBody AtualizarLotacaoDTO lotacao) {
        Lotacao atualizado = lotacaoService.atualizarPorId(id, lotacaoMapper.toEntity(lotacao));
        return ResponseEntity.ok(lotacaoMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        lotacaoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}