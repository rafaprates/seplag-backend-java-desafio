package com.seplag.servidores.compartilhado.dtos.request;

import com.seplag.servidores.compartilhado.entities.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarCidadeDTO(
        @NotNull @NotBlank Long id,
        @NotNull @NotBlank String nome,
        @NotNull Estado uf
) { }
