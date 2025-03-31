package com.seplag.servidores.compartilhado.dtos.request;

import com.seplag.servidores.compartilhado.entities.TipoLogradouro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CriarEnderecoDTO(
        TipoLogradouro tipoLogradouro,
        @NotNull @NotBlank String logradouro,
        @Positive Integer numero,
        @NotNull String bairro,
        @NotNull Long cidadeId
) { }
