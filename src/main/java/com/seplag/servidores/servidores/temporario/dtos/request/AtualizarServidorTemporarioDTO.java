package com.seplag.servidores.servidores.temporario.dtos.request;

import com.seplag.servidores.compartilhado.dtos.request.AtualizarPessoaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record AtualizarServidorTemporarioDTO(
        @Future LocalDate dataAdmissao,
        @Future LocalDate dataDemissao,
        @Valid AtualizarPessoaDTO pessoa
) { }
