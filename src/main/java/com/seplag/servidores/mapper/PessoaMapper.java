package com.seplag.servidores.mapper;

import com.seplag.servidores.dto.request.AtualizarPessoaDTO;
import com.seplag.servidores.dto.request.CriarPessoaDTO;
import com.seplag.servidores.dto.response.EnderecoResponseDTO;
import com.seplag.servidores.dto.response.FotoResponseDTO;
import com.seplag.servidores.dto.response.PessoaResponseDTO;
import com.seplag.servidores.entity.Endereco;
import com.seplag.servidores.entity.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PessoaMapper {

    private final EnderecoMapper enderecoMapper;
    private final FotoMapper fotoMapper;

    public Pessoa toEntity(CriarPessoaDTO pessoaDTO) {
        Set<Endereco> enderecos = pessoaDTO
                .enderecos()
                .stream()
                .map(enderecoMapper::toEntity)
                .collect(Collectors.toSet());

        return new Pessoa(
                pessoaDTO.nome(),
                pessoaDTO.dataNascimento(),
                pessoaDTO.sexo(),
                pessoaDTO.mae(),
                pessoaDTO.pai(),
                enderecos
        );
    }

    public Pessoa toEntity(AtualizarPessoaDTO pessoaDTO) {
        Set<Endereco> enderecos = pessoaDTO
                .enderecos()
                .stream()
                .map(enderecoMapper::toEntity)
                .collect(Collectors.toSet());

        return new Pessoa(
                pessoaDTO.nome(),
                pessoaDTO.dataNascimento(),
                pessoaDTO.sexo(),
                pessoaDTO.mae(),
                pessoaDTO.pai(),
                enderecos
        );
    }

    public PessoaResponseDTO toDTO(Pessoa pessoa) {
        Set<EnderecoResponseDTO> enderecos = pessoa
                .getEnderecos()
                .stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toSet());

        Set<FotoResponseDTO> fotos = pessoa
                .getFotos()
                .stream().
                map(fotoMapper::toDTO)
                .collect(Collectors.toSet());

        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getSexo(),
                pessoa.getMae(),
                pessoa.getPai(),
                enderecos,
                fotos
        );
    }

}
