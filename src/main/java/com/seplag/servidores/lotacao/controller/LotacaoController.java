package com.seplag.servidores.lotacao.controller;

import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.mappers.EnderecoMapper;
import com.seplag.servidores.lotacao.dtos.request.AtualizarLotacaoDTO;
import com.seplag.servidores.lotacao.dtos.request.CriarLotacaoDTO;
import com.seplag.servidores.lotacao.dtos.response.LotacaoResponseDTO;
import com.seplag.servidores.lotacao.entity.Lotacao;
import com.seplag.servidores.lotacao.mapper.LotacaoMapper;
import com.seplag.servidores.lotacao.service.LotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lotacoes")
@RequiredArgsConstructor
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

    @GetMapping("/filtros")
    public ResponseEntity<Page<EnderecoResponseDTO>> filtrarPorNomeServidor(@RequestParam String nomeServidor, Pageable pageable) {
        Page<Lotacao> lotacoesPage = lotacaoService.filtrarPorNomeServidor(nomeServidor, pageable);

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