package com.seplag.servidores.service;

import com.seplag.servidores.dto.response.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.entity.Endereco;
import com.seplag.servidores.entity.Unidade;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.repository.UnidadeJdbcRepository;
import com.seplag.servidores.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoService enderecoService;
    private final UnidadeJdbcRepository unidadeJdbcRepository;

    public Long criar(Unidade novaUnidade) {
        log.info("Criando nova unidade: {}", novaUnidade);
        return unidadeRepository.save(novaUnidade).getId();
    }

    public Page<Unidade> buscarTodas(Pageable pageable) {
        log.info("Buscando todas as unidades");
        return unidadeRepository.findAll(pageable);
    }

    public Optional<Unidade> buscarPorId(Long id) {
        log.info("Buscando unidade com id {}", id);
        return unidadeRepository.findById(id);
    }

    public Page<ServidorEfetivoUnidadeResponseDTO> buscarServidoresEfetivosPorUnidadeId(Long id, Pageable pageable) {
        return unidadeJdbcRepository.buscarServidoresEfetivosPorUnidadeId(id, pageable);
    }

    public void atualizarPorId(Long id, Unidade unidade) {
        log.info("Atualizando unidade com id {}", id);

        if (!existsById(id))
            throw new RecursoNaoEncontradoException("Unidade com id %d não encontrada".formatted(id));

        Set<Endereco> enderecosAtualizados = unidade
                .getEnderecos()
                .stream()
                .map(e -> enderecoService.atualizarPorId(e.getId(), e))
                .collect(Collectors.toSet());

        Unidade unidadeAtualizada = new Unidade(
                id,
                unidade.getNome(),
                unidade.getSigla(),
                enderecosAtualizados
        );

        unidadeRepository.save(unidadeAtualizada);
    }

    public void deleteById(Long id) {
        log.info("Deletando unidade com id {}", id);
        Unidade unidade = unidadeRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade não encontrada com o id %d".formatted(id)));

        deletarEnderecosAssociados(unidade);

        unidadeRepository.delete(unidade);
    }

    private boolean existsById(Long id) {
        return unidadeRepository.existsById(id);
    }

    private void deletarEnderecosAssociados(Unidade unidade) {
        unidade.getEnderecos().clear();
        unidadeRepository.save(unidade);
    }
}
