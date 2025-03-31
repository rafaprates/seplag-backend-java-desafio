package com.seplag.servidores.compartilhado.dtos.request;

import com.seplag.servidores.compartilhado.entities.Sexo;

import java.time.LocalDate;
import java.util.Set;

public record CriarPessoaDTO(
        String nome,
        String mae,
        String pai,
        LocalDate dataNascimento,
        Sexo sexo,
        Set<NovoEnderecoRequest> enderecos
) { }
