package com.seplag.servidores.servidores.efetivo.services;

import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.compartilhado.services.EnderecoService;
import com.seplag.servidores.compartilhado.services.PessoaService;
import com.seplag.servidores.servidores.efetivo.entities.ServidorEfetivo;
import com.seplag.servidores.servidores.efetivo.repository.ServidorEfetivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorEfetivoService {

    private final PessoaService pessoaService;
    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final EnderecoService enderecoService;

    public ServidorEfetivo registrarServidorEfetivo(ServidorEfetivo servidor) {
        log.info("Registrando novo servidor temporário: {}", servidor);
        pessoaService.registrarPessoa(servidor);
        return servidorEfetivoRepository.save(servidor);
    }

    public Page<ServidorEfetivo> buscarTodos(Pageable pageable) {
        log.info("Buscando todos os servidores temporários");
        return servidorEfetivoRepository.findAll(pageable);
    }

    public ServidorEfetivo buscarPorId(Long id) {
        log.info("Buscando servidor temporário com ID: {}", id);
        return servidorEfetivoRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Servidor temporário não encontrado"));
    }

    public ServidorEfetivo atualizarPorId(Long id, ServidorEfetivo servidor) {
        log.info("Atualizando servidor temporário: {}", servidor);

        Set<Endereco> enderecosAtualizados = servidor
                .getEnderecos()
                .stream()
                .map(e -> enderecoService.atualizarPorId(e.getId(), e))
                .collect(Collectors.toSet());

        servidor.setEnderecos(enderecosAtualizados);

        pessoaService.atualizarPorId(id, servidor);
        return servidorEfetivoRepository.save(servidor);
    }

    public void deletarPorId(Long id) {
        log.info("Deletando servidor temporário com ID: {}", id);
        ServidorEfetivo servidorTemporario = buscarPorId(id);
        servidorEfetivoRepository.delete(servidorTemporario);
    }
}
