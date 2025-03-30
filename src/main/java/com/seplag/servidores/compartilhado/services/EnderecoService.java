package com.seplag.servidores.compartilhado.services;

import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.repositories.EnderecoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final CidadeService cidadeService;

    public Endereco criar(@Valid Endereco endereco) {
        log.info("Criando novo endereço: {}", endereco);

        Cidade cidade = cidadeService.findById(endereco.getCidade().getId());
        endereco.setCidade(cidade);

        return enderecoRepository.save(endereco);
    }
}
