package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@AllArgsConstructor
public class PessoaResponseDTO {

    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String mae;
    private String pai;
    Set<EnderecoResponseDTO> enderecos;
    Set<FotoResponseDTO> fotos;

}
