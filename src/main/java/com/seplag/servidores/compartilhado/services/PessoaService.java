package com.seplag.servidores.compartilhado.services;

import com.seplag.servidores.compartilhado.entities.Pessoa;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.compartilhado.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;

    public Pessoa registrarPessoa(Pessoa pessoa) {
        log.info("Registrando nova pessoa: {}", pessoa);

        pessoa
                .getEnderecos()
                .forEach(enderecoService::criar);

        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPorId(Long id, Pessoa pessoa) {
        log.info("Atualizando pessoa: {}", pessoa);

        Pessoa pessoaExistente = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));

        pessoa.setId(pessoaExistente.getId());

        return pessoaRepository.save(pessoa);
    }
}
