package com.seplag.servidores.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO {

    private Long id;
    private String tipoLogradouro;
    private String logradouro;
    private int numero;
    private String bairro;
    private CidadeResponseDTO cidade;

}
