package com.seplag.servidores.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LotacaoResponseDTO {

    private long id;
    private PessoaResponseDTO servidor;
    private UnidadeResponseDTO unidade;
    private LocalDate dataLotacao;
    private LocalDate dataRemocao;
    private String portaria;

}
