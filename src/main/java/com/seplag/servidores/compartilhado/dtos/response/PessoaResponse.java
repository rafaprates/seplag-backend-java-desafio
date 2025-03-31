package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PessoaResponse {

    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String mae;
    private String pai;
    Set<EnderecoResponse> enderecos;

}
