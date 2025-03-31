package com.seplag.servidores.lotacao.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AtualizarLotacaoDTO {

    @NotNull
    private Long pessoaId;

    @NotNull
    private Long unidadeId;

    private LocalDate dataLotacao;

    private LocalDate dataRemocao;

    @NotEmpty
    private String portaria;

}