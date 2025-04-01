package com.seplag.servidores.unidade.services;

import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.compartilhado.mappers.EnderecoMapper;
import com.seplag.servidores.compartilhado.services.CidadeService;
import com.seplag.servidores.compartilhado.services.EnderecoService;
import com.seplag.servidores.unidade.dtos.responses.ServidorEfetivoUnidadeResponseDTO;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.mapper.ServidorEfetivoUnidadeMapper;
import com.seplag.servidores.unidade.repositories.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoService enderecoService;
    private final CidadeService cidadeService;
    private final EnderecoMapper enderecoMapper;
    private final ServidorEfetivoUnidadeMapper servidorEfetivoUnidadeMapper;
    private final JdbcClient jdbcClient;

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

    public List<ServidorEfetivoUnidadeResponseDTO> buscarServidoresEfetivosPorUnidadeId(Long id, Pageable pageable) {
        final String sql = """
                SELECT
                  u.*, p.*, fp.*
                FROM lotacao l
                INNER JOIN unidade u ON u.unid_id = l.unid_id
                INNER JOIN servidor_efetivo se ON se.pes_id = l.pes_id
                INNER JOIN pessoa p ON p.pes_id = se.pes_id
                LEFT JOIN foto_pessoa fp ON fp.pes_id = se.pes_id
                WHERE u.unid_id = :unidadeId
                """;

        return jdbcClient
                .sql(sql)
                .param("unidadeId", id)
                .query(servidorEfetivoUnidadeMapper);
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

        unidadeRepository.delete(unidade);
    }

    private boolean existsById(Long id) {
        return unidadeRepository.existsById(id);
    }
}
