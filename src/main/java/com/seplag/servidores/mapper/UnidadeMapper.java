package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.response.EnderecoResponseDTO;
import com.seplag.servidores.dto.request.CriarUnidadeDTO;
import com.seplag.servidores.dto.request.UnidadeUpdateRequest;
import com.seplag.servidores.dto.response.UnidadeResponseDTO;
import com.seplag.servidores.entity.Unidade;
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
