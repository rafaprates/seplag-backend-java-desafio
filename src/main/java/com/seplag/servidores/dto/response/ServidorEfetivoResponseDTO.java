package com.seplag.servidores.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorEfetivoResponseDTO extends PessoaResponseDTO {

    private String matricula;

    public ServidorEfetivoResponseDTO(PessoaResponseDTO pessoaDTO, String matricula) {
        super(pessoaDTO.getId(), pessoaDTO.getNome(), pessoaDTO.getDataNascimento(), pessoaDTO.getSexo(), pessoaDTO.getMae(), pessoaDTO.getPai(), pessoaDTO.getEnderecos(), pessoaDTO.getFotos());
        this.matricula = matricula;
    }
}
