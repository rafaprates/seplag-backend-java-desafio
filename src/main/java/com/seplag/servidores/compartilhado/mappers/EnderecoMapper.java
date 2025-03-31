package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.request.CriarEnderecoDTO;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntity(CriarEnderecoDTO request) {
        return new Endereco(
                request.tipoLogradouro(),
                request.logradouro(),
                request.numero(),
                request.bairro(),
                new Cidade(request.cidadeId())
        );
    }
}
