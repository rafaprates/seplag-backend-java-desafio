package com.seplag.servidores.compartilhado.controllers;

import com.seplag.servidores.compartilhado.dtos.request.NovaCidadeRequest;
import com.seplag.servidores.compartilhado.dtos.response.RecursoIdResponse;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.services.CidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService cidadeService;
    private final ModelMapper mapper;

    @PostMapping("/api/v1/cidades")
    public ResponseEntity<RecursoIdResponse> criarNovaCidade(@Valid @RequestBody NovaCidadeRequest request) {
        Long id = cidadeService.criar(
                mapper.map(request, Cidade.class)
        );

        return ResponseEntity.ok(new RecursoIdResponse(id));
    }

}
