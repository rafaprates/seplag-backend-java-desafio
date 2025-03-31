package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.request.CriarEnderecoDTO;
import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoMapper {

    private final CidadeMapper cidadeMapper;

    public Endereco toEntity(CriarEnderecoDTO request) {
        return new Endereco(
                request.tipoLogradouro(),
                request.logradouro(),
                request.numero(),
                request.bairro(),
                new Cidade(request.cidadeId())
        );
    }

    public EnderecoResponseDTO toDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getTipoLogradouro().toString(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                cidadeMapper.toDTO(endereco.getCidade())
        );
    }
}
