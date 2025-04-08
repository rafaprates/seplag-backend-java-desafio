package com.seplag.servidores.dto.response;

import com.seplag.servidores.entity.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@AllArgsConstructor
public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;
    private String mae;
    private String pai;
    Set<EnderecoResponseDTO> enderecos;
    Set<FotoResponseDTO> fotos;

}
