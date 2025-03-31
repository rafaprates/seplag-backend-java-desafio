package com.seplag.servidores.unidade.dtos.responses;

import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UnidadeResponse {

    private Long id;
    private String nome;
    private String sigla;
    private Set<EnderecoResponseDTO> enderecos;

}
