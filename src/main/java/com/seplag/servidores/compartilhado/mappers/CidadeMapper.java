package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.request.CriarCidadeDTO;
import com.seplag.servidores.compartilhado.dtos.response.CidadeResponseDTO;
import com.seplag.servidores.compartilhado.entities.Cidade;
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
