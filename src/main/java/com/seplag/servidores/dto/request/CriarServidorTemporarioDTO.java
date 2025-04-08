package com.seplag.servidores.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record CriarServidorTemporarioDTO(
        @Future LocalDate dataAdmissao,
        @Future LocalDate dataDemissao,
        @Valid CriarPessoaDTO pessoa
) { }
