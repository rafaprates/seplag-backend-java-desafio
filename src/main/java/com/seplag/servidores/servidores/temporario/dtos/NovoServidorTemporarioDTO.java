package com.seplag.servidores.servidores.temporario.dtos;

import com.seplag.servidores.compartilhado.dtos.request.CriarPessoaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

public record NovoServidorTemporarioDTO(
        @Future LocalDate dataAdmissao,
        @Future LocalDate dataDemissao,
        @Valid CriarPessoaDTO pessoa
) { }
