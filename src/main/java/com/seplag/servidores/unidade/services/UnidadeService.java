package com.seplag.servidores.unidade.services;

import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.mappers.EnderecoMapper;
import com.seplag.servidores.compartilhado.services.EnderecoService;
import com.seplag.servidores.unidade.dtos.UnidadeRequest;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.repositories.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    public Long criar(UnidadeRequest novaUnidade) {
        log.info("Criando nova unidade: {}", novaUnidade);

        Endereco endereco = enderecoMapper.toEntity(novaUnidade.endereco());

        endereco = enderecoService.criar(endereco);

        Unidade unidade = new Unidade(
                novaUnidade.nome(),
                novaUnidade.sigla(),
                endereco
        );

        return unidadeRepository.save(unidade).getId();
    }
}
