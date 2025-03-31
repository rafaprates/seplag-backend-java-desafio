package com.seplag.servidores.servidores.temporario.services;

import com.seplag.servidores.compartilhado.services.PessoaService;
import com.seplag.servidores.servidores.temporario.entities.ServidorTemporario;
import com.seplag.servidores.servidores.temporario.repository.ServidorTemporarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServidorTemporarioService {

    private final PessoaService pessoaService;
    private final ServidorTemporarioRepository servidorTemporarioRepository;

    public ServidorTemporario registrarServidorTemporario(ServidorTemporario servidorTemporario) {
        log.info("Registrando novo servidor temporário: {}", servidorTemporario);
        pessoaService.registrarPessoa(servidorTemporario);
        return servidorTemporarioRepository.save(servidorTemporario);
    }


    public Page<ServidorTemporario> buscarTodos(Pageable pageable) {
        log.info("Buscando todos os servidores temporários");
        return servidorTemporarioRepository.findAll(pageable);
    }
}
