package com.seplag.servidores.servidores.efetivo.dtos.request;

import com.seplag.servidores.compartilhado.dtos.request.AtualizarPessoaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AtualizarServidorEfetivoDTO(
        @NotNull @NotEmpty String matricula,
        @Valid AtualizarPessoaDTO pessoa
) {

}
