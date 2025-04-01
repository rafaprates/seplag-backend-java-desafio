package com.seplag.servidores.servidores.efetivo.dtos.response;

import com.seplag.servidores.compartilhado.dtos.response.PessoaResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServidorEfetivoResponseDTO extends PessoaResponseDTO {

    private String matricula;

    public ServidorEfetivoResponseDTO(PessoaResponseDTO pessoaDTO, String matricula) {
        super(pessoaDTO.getNome(), pessoaDTO.getDataNascimento(), pessoaDTO.getSexo(), pessoaDTO.getMae(), pessoaDTO.getPai(), pessoaDTO.getEnderecos(), pessoaDTO.getFotos());
        this.matricula = matricula;
    }
}
