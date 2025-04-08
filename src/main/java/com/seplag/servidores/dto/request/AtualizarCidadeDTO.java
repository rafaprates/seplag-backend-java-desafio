package com.seplag.servidores.dto.request;

import com.seplag.servidores.entity.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarCidadeDTO(
        @NotNull @NotBlank Long id,
        @NotNull @NotBlank String nome,
        @NotNull Estado uf
) { }
