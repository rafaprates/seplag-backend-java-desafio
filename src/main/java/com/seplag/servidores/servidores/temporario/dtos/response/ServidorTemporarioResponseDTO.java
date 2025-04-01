package com.seplag.servidores.servidores.temporario.dtos.response;

import com.seplag.servidores.compartilhado.dtos.response.PessoaResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServidorTemporarioResponseDTO extends PessoaResponseDTO {

    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;

    public ServidorTemporarioResponseDTO(PessoaResponseDTO pessoaDTO, LocalDate dataAdmissao, LocalDate dataDemissao) {
        super(pessoaDTO.getId(), pessoaDTO.getNome(), pessoaDTO.getDataNascimento(), pessoaDTO.getSexo(), pessoaDTO.getMae(), pessoaDTO.getPai(), pessoaDTO.getEnderecos(), pessoaDTO.getFotos());
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
    }
}
