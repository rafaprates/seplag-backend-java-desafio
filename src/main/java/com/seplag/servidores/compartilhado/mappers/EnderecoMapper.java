package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.request.AtualizarEnderecoDTO;
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

    public Endereco toEntity(CriarEnderecoDTO enderecoDTO) {
        return new Endereco(
                enderecoDTO.tipoLogradouro(),
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.bairro(),
                new Cidade(enderecoDTO.cidadeId())
        );
    }

    public Endereco toEntity(AtualizarEnderecoDTO enderecoDTO) {
        return new Endereco(
                enderecoDTO.id(),
                enderecoDTO.tipoLogradouro(),
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.bairro(),
                new Cidade(enderecoDTO.cidadeId())
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
