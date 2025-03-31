package com.seplag.servidores.unidade.dtos.requests;

import com.seplag.servidores.compartilhado.dtos.request.AtualizarEnderecoDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnidadeUpdateRequest(
        @NotNull @NotBlank String nome,
        @Nullable String sigla,
        @Valid @NotNull AtualizarEnderecoDTO endereco
) { }
