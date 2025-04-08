package com.seplag.servidores.dto.response;

import com.seplag.servidores.entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeResponseDTO {

    private Long id;
    private String nome;
    private Estado uf;

}
