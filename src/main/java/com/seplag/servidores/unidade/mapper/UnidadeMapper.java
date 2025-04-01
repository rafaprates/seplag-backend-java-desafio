package com.seplag.servidores.unidade.mapper;

import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import com.seplag.servidores.compartilhado.mappers.EnderecoMapper;
import com.seplag.servidores.unidade.dtos.requests.CriarUnidadeDTO;
import com.seplag.servidores.unidade.dtos.requests.UnidadeUpdateRequest;
import com.seplag.servidores.unidade.dtos.responses.UnidadeResponseDTO;
import com.seplag.servidores.unidade.entities.Unidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnidadeMapper {

    private final EnderecoMapper enderecoMapper;

    public Unidade toEntity(CriarUnidadeDTO unidadeDTO) {
        return new Unidade(
                unidadeDTO.nome(),
                unidadeDTO.sigla(),
                enderecoMapper.toEntity(unidadeDTO.endereco())
        );
    }

    public Unidade toEntity(UnidadeUpdateRequest unidadeDTO) {
        return new Unidade(
                unidadeDTO.nome(),
                unidadeDTO.sigla(),
                enderecoMapper.toEntity(unidadeDTO.endereco())
        );
    }

    public UnidadeResponseDTO toDTO(Unidade unidade) {
        Set<EnderecoResponseDTO> enderecos = unidade
                .getEnderecos()
                .stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toSet());

        return new UnidadeResponseDTO(
                unidade.getId(),
                unidade.getNome(),
                unidade.getSigla(),
                enderecos
        );
    }

}
