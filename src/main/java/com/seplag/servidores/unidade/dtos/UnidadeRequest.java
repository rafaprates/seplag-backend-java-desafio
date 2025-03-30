package com.seplag.servidores.unidade.dtos;

import com.seplag.servidores.compartilhado.dtos.request.NovoEnderecoRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnidadeRequest(
        @NotNull @NotBlank String nome,
        @Nullable String sigla,
        @Valid @NotNull NovoEnderecoRequest endereco
) { }
