package com.seplag.servidores.compartilhado.mappers;

import com.seplag.servidores.compartilhado.dtos.response.EnderecoResponseDTO;
import com.seplag.servidores.compartilhado.dtos.response.PessoaResponseDTO;
import com.seplag.servidores.compartilhado.entities.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PessoaMapper {

    private final EnderecoMapper enderecoMapper;

    public PessoaResponseDTO toDTO(Pessoa pessoa) {
        Set<EnderecoResponseDTO> enderecos = pessoa
                .getEnderecos()
                .stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toSet());

        return new PessoaResponseDTO(
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getSexo(),
                pessoa.getMae(),
                pessoa.getPai(),
                enderecos
        );
    }

}
