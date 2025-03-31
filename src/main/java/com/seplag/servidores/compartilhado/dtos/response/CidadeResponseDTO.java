package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResponseDTO {

    private Long id;
    private String nome;
    private Estado uf;

}
