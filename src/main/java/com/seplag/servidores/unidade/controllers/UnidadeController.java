package com.seplag.servidores.unidade.controllers;

import com.seplag.servidores.compartilhado.dtos.response.RecursoIdResponse;
import com.seplag.servidores.unidade.dtos.UnidadeRequest;
import com.seplag.servidores.unidade.services.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping("/api/v1/unidades")
    public ResponseEntity<RecursoIdResponse> criarUnidade(@Valid @RequestBody UnidadeRequest novaUnidade) {
        Long id = unidadeService.criar(novaUnidade);
        return ResponseEntity.ok(new RecursoIdResponse(id));
    }

}
