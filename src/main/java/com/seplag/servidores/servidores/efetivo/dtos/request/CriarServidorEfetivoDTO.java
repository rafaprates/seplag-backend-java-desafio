package com.seplag.servidores.servidores.efetivo.dtos.request;

import com.seplag.servidores.compartilhado.dtos.request.CriarPessoaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarServidorEfetivoDTO(
        @NotNull @NotEmpty String matricula,
        @Valid CriarPessoaDTO pessoa
) { }
