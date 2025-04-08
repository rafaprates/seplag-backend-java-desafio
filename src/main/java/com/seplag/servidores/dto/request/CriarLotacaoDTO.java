package com.seplag.servidores.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CriarLotacaoDTO {

    private Long pessoaId;
    private Long unidadeId;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;

}