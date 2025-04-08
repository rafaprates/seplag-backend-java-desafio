package com.seplag.servidores.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarServidorEfetivoDTO(
        @NotNull @NotEmpty String matricula,
        @Valid CriarPessoaDTO pessoa
) { }
