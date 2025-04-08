package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.request.CriarCidadeDTO;
import com.seplag.servidores.dto.response.CidadeResponseDTO;
import com.seplag.servidores.entity.Cidade;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {

    public Cidade toEntity(CriarCidadeDTO cidadeDTO) {
        return new Cidade(
                cidadeDTO.nome(),
                cidadeDTO.uf()
        );
    }

    public CidadeResponseDTO toDTO(Cidade cidade) {
        return new CidadeResponseDTO(
                cidade.getId(),
                cidade.getNome(),
                cidade.getUf()
        );
    }

}
