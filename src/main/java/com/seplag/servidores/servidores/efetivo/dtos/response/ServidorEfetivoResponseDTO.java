package com.seplag.servidores.servidores.efetivo.dtos.response;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorEfetivoResponseDTO extends Pessoa {

    private String matricula;

}
