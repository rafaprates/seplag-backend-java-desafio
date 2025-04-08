package com.seplag.servidores.dto.request;

import com.seplag.servidores.entity.TipoLogradouro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarEnderecoDTO(
        @NotNull Long id,
        TipoLogradouro tipoLogradouro,
        @NotNull @NotBlank String logradouro,
        @Positive Integer numero,
        @NotNull String bairro,
        @NotNull Long cidadeId
) { }
