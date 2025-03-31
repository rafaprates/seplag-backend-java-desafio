package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CidadeResponseDTO {

    private Long id;
    private String nome;
    private Estado uf;

}
