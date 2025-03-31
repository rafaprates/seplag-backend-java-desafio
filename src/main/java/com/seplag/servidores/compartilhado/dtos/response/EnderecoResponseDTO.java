package com.seplag.servidores.compartilhado.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnderecoResponseDTO {

    private Long id;
    private String tipoLogradouro;
    private String logradouro;
    private int numero;
    private String bairro;
    private CidadeResponseDTO cidade;

}
