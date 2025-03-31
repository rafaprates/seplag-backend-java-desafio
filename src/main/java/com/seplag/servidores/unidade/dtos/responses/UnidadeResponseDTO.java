package com.seplag.servidores.unidade.dtos.responses;

import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeResponseDTO {

    private Long id;
    private String nome;
    private String sigla;
    private Set<EnderecoResponseDTO> enderecos;

}
