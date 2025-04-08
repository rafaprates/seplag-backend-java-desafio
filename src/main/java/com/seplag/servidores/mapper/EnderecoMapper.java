package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.request.AtualizarEnderecoDTO;
import com.seplag.servidores.dto.request.CriarEnderecoDTO;
import com.seplag.servidores.dto.response.EnderecoResponseDTO;
import com.seplag.servidores.entity.Cidade;
import com.seplag.servidores.entity.Endereco;
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
