package com.seplag.servidores.dto.request;

import com.seplag.servidores.entity.Sexo;

import java.time.LocalDate;
import java.util.Set;

public record AtualizarPessoaDTO(
        Long id,
        String nome,
        String mae,
        String pai,
        LocalDate dataNascimento,
        Sexo sexo,
        Set<AtualizarEnderecoDTO> enderecos
) {

}
