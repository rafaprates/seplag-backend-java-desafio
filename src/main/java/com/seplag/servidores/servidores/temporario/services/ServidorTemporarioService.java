package com.seplag.servidores.servidores.temporario.services;

import com.seplag.servidores.compartilhado.entities.Endereco;
import com.seplag.servidores.compartilhado.exceptions.RecursoNaoEncontradoException;
import com.seplag.servidores.compartilhado.services.EnderecoService;
import com.seplag.servidores.compartilhado.services.PessoaService;
import com.seplag.servidores.servidores.temporario.entities.ServidorTemporario;
import com.seplag.servidores.servidores.temporario.repository.ServidorTemporarioRepository;
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
public class ServidorTemporarioService {

    private final PessoaService pessoaService;
    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final EnderecoService enderecoService;

    public ServidorTemporario registrarServidorTemporario(ServidorTemporario novoServidor) {
        log.info("Registrando novo servidor temporário: {}", novoServidor);
        pessoaService.registrarPessoa(novoServidor);
        return servidorTemporarioRepository.save(novoServidor);
    }

    public Page<ServidorTemporario> buscarTodos(Pageable pageable) {
        log.info("Buscando todos os servidores temporários");
        return servidorTemporarioRepository.findAll(pageable);
    }

    public ServidorTemporario buscarPorId(Long id) {
        log.info("Buscando servidor temporário com ID: {}", id);
        return servidorTemporarioRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Servidor temporário não encontrado"));
    }

    public ServidorTemporario atualizarPorId(Long id, ServidorTemporario novoServidor) {
        log.info("Atualizando servidor temporário: {}", novoServidor);

        Set<Endereco> enderecosAtualizados = novoServidor
                .getEnderecos()
                .stream()
                .map(e -> enderecoService.atualizarPorId(e.getId(), e))
                .collect(Collectors.toSet());

        novoServidor.setEnderecos(enderecosAtualizados);

        pessoaService.atualizarPorId(id, novoServidor);
        return servidorTemporarioRepository.save(novoServidor);
    }
}
