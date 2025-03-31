package com.seplag.servidores.compartilhado.dtos.response;

import com.seplag.servidores.compartilhado.entities.TipoLogradouro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseDTO {

    private Long id;
    private String tipoLogradouro;
    private String logradouro;
    private int numero;
    private String bairro;
    private CidadeResponseDTO cidade;

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro.getDescricao();
    }
}
