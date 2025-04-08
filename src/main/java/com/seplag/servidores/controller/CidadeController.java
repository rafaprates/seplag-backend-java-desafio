package com.seplag.servidores.controller;

import com.seplag.servidores.dto.request.CriarCidadeDTO;
import com.seplag.servidores.dto.response.RecursoCriadoDTO;
import com.seplag.servidores.mapper.CidadeMapper;
import com.seplag.servidores.service.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cidades")
@Tag(name = "Cidades", description = "Cria cidades que serão atribuídas aos endereços dos servidores ou das unidades")
public class CidadeController {

    private final CidadeService cidadeService;
    private final CidadeMapper cidadeMapper;

    @PostMapping
    public ResponseEntity<RecursoCriadoDTO> criarNovaCidade(@Valid @RequestBody CriarCidadeDTO request) {
        Long id = cidadeService.criar(cidadeMapper.toEntity(request));
        return ResponseEntity.ok(new RecursoCriadoDTO(id));
    }

}
