package com.seplag.servidores.service;

import com.seplag.servidores.entity.Cidade;
import com.seplag.servidores.exception.RecursoJaExisteException;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public Long criar(Cidade cidade) {
        if (cidadeRepository.existsByNomeAndUf(cidade.getNome(), cidade.getUf()))
            throw new RecursoJaExisteException("Cidade %s - %s já está cadastrada".formatted(cidade.getNome(), cidade.getUf()));

        return cidadeRepository.save(cidade).getId();
    }

    public Cidade buscarPorId(Long id) {
        if (id == null)
            throw new RecursoNaoEncontradoException("Cidade não encontrada");

        return cidadeRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cidade não encontrada"));
    }
}
