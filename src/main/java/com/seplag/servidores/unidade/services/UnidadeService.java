package com.seplag.servidores.unidade.services;

import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.compartilhado.mappers.EnderecoMapper;
import com.seplag.servidores.compartilhado.services.CidadeService;
import com.seplag.servidores.compartilhado.services.EnderecoService;
import com.seplag.servidores.unidade.dtos.requests.NovaUnidadeRequest;
import com.seplag.servidores.unidade.dtos.requests.UnidadeUpdateRequest;
import com.seplag.servidores.unidade.entities.Unidade;
import com.seplag.servidores.unidade.repositories.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoService enderecoService;
    private final CidadeService cidadeService;
    private final EnderecoMapper enderecoMapper;

    public Long criar(NovaUnidadeRequest novaUnidade) {
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

    public Page<Unidade> buscarTodas(Pageable pageable) {
        log.info("Buscando todas as unidades");
        return unidadeRepository.findAll(pageable);
    }

    public Optional<Unidade> buscarPorId(Long id) {
        log.info("Buscando unidade com id {}", id);
        return unidadeRepository.findById(id);
    }

    public void atualizarPorId(Long id, UnidadeUpdateRequest unidade) {
        log.info("Atualizando unidade com id {}", id);

        if (!existsById(id))
            throw new RecursoNaoEncontradoException("Unidade com id %d não encontrada".formatted(id));

        Cidade cidadeAtualizada = cidadeService.findById(unidade.endereco().cidadeId());

        Endereco enderecoAtualizado = new Endereco(
                unidade.endereco().id(),
                unidade.endereco().tipoLogradouro(),
                unidade.endereco().logradouro(),
                unidade.endereco().numero(),
                unidade.endereco().bairro(),
                cidadeAtualizada
        );

        enderecoAtualizado = enderecoService.atualizarPorId(enderecoAtualizado.getId(), enderecoAtualizado);

        Unidade unidadeAtualizada = new Unidade(
                id,
                unidade.nome(),
                unidade.sigla(),
                Set.of(enderecoAtualizado)
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
