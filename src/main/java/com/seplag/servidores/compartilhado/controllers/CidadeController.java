package com.seplag.servidores.compartilhado.controllers;

import com.seplag.servidores.compartilhado.dtos.request.CriarCidadeDTO;
import com.seplag.servidores.compartilhado.dtos.response.RecursoCriadoDTO;
import com.seplag.servidores.compartilhado.mappers.CidadeMapper;
import com.seplag.servidores.compartilhado.services.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cidades", description = "Cria cidades que serão atribuídas aos endereços dos servidores ou das unidades")
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeMapper cidadeMapper;

    @PostMapping("/api/v1/cidades")
    public ResponseEntity<RecursoCriadoDTO> criarNovaCidade(@Valid @RequestBody CriarCidadeDTO request) {
        Long id = cidadeService.criar(cidadeMapper.toEntity(request));
        return ResponseEntity.ok(new RecursoCriadoDTO(id));
    }

}
