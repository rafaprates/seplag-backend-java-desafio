package com.seplag.servidores.lotacao.dtos.response;

import com.seplag.servidores.compartilhado.dtos.response.PessoaResponseDTO;
import com.seplag.servidores.unidade.dtos.responses.UnidadeResponseDTO;
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
