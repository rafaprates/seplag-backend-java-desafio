package com.seplag.servidores.dto.request;

import com.seplag.servidores.entity.Sexo;

import java.time.LocalDate;
import java.util.Set;

public record CriarPessoaDTO(
        String nome,
        String mae,
        String pai,
        LocalDate dataNascimento,
        Sexo sexo,
        Set<CriarEnderecoDTO> enderecos
) { }
