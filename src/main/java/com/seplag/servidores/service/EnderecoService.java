package com.seplag.servidores.service;

import com.seplag.servidores.entity.Cidade;
import com.seplag.servidores.entity.Endereco;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.repository.EnderecoRepository;
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

    public Endereco buscarPorId(Long id) {
        if (id == null) throw new RecursoNaoEncontradoException("Endereço não encontrado");
        return enderecoRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereço não encontrado"));
    }

    public Endereco criar(@Valid Endereco endereco) {
        log.info("Criando novo endereço: {}", endereco);

        Cidade cidade = cidadeService.buscarPorId(endereco.getCidade().getId());
        endereco.setCidade(cidade);

        return enderecoRepository.save(endereco);
    }

    public Endereco atualizarPorId(Long enderecoId, @Valid Endereco endereco) {
        log.info("Atualizando endereço com id {}", enderecoId);

        if (!existsById(enderecoId))
            throw new RecursoNaoEncontradoException("Endereço com id %d não encontrado".formatted(enderecoId));

        Endereco enderecoAtualizado = new Endereco(
                endereco.getId(),
                endereco.getTipoLogradouro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade()
        );

        return enderecoRepository.save(enderecoAtualizado);
    }

    public void deleteById(Long id) {
        log.info("Deletando endereço com id {}", id);
        Endereco endereco = buscarPorId(id);
        enderecoRepository.delete(endereco);
    }

    private boolean existsById(Long id) {
        return enderecoRepository.existsById(id);
    }
}
