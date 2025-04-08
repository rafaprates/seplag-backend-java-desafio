package com.seplag.servidores.service;

import com.seplag.servidores.entity.Pessoa;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.entity.Lotacao;
import com.seplag.servidores.repository.LotacaoRepository;
import com.seplag.servidores.entity.Unidade;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final PessoaService pessoaService;
    private final UnidadeService unidadeService;

    public Lotacao criar(Lotacao lotacao) {
        log.info("Criando nova lotação para pessoa ID: {}", lotacao.getPessoa().getId());
        return lotacaoRepository.save(lotacao);
    }

    public Lotacao buscarPorId(Long id) {
        return lotacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lotação não encontrada com ID: " + id));
    }

    public List<Lotacao> listarTodas() {
        return lotacaoRepository.findAll();
    }

    public Page<Lotacao> filtrarPorNomeServidor(String nomeServidor, Pageable pageable) {
        return lotacaoRepository.findByPessoaNomeContainingIgnoreCase(nomeServidor, pageable);
    }

    public Lotacao atualizarPorId(Long id, Lotacao novaLotacao) {
        if (!existePorId(id))
            throw new RecursoNaoEncontradoException("Lotação não encontrada com ID: " + id);

        Pessoa novaPessoa = pessoaService
                .buscarPorId(novaLotacao.getPessoa().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada com ID: " + novaLotacao.getId()));

        Unidade novaUnidade = unidadeService
                .buscarPorId(novaLotacao.getUnidade().getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada com ID: " + novaLotacao.getId()));

        Lotacao atualizada = new Lotacao(
                id,
                novaPessoa,
                novaUnidade,
                novaLotacao.getDataLotacao(),
                novaLotacao.getDataRemocao(),
                novaLotacao.getPortaria()
        );

        log.info("Atualizando lotação ID: {}", id);
        return lotacaoRepository.save(atualizada);
    }

    public void deletarPorId(Long id) {
        Lotacao existente = buscarPorId(id);
        log.info("Deletando lotação ID: {}", id);
        lotacaoRepository.delete(existente);
    }

    private boolean existePorId(Long id) {
        return lotacaoRepository.existsById(id);
    }

}