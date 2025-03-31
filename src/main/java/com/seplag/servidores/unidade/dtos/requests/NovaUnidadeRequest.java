package com.seplag.servidores.unidade.dtos.requests;

import com.seplag.servidores.compartilhado.dtos.request.CriarEnderecoDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovaUnidadeRequest(
        @NotNull @NotBlank String nome,
        @Nullable String sigla,
        @Valid @NotNull CriarEnderecoDTO endereco
) { }
