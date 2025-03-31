package com.seplag.servidores.servidores.temporario.dtos;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServidorTemporarioResponse extends Pessoa {

    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;

}
