package com.seplag.servidores.compartilhado.services;

import com.seplag.servidores.compartilhado.entities.Cidade;
import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
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

    private boolean existsById(Long id) {
        return enderecoRepository.existsById(id);
    }
}
