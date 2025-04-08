package com.seplag.servidores.service;

import com.seplag.servidores.entity.Foto;
import com.seplag.servidores.entity.Pessoa;
import com.seplag.servidores.exception.RecursoNaoEncontradoException;
import com.seplag.servidores.repository.FotoRepository;
import com.seplag.servidores.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    @Value("${bucket.buckets.fotos-servidores.nome}")
    private String fotoServidoresBucket;

    private final PessoaRepository pessoaRepository;
    private final FotoRepository fotoRepository;
    private final EnderecoService enderecoService;
    private final MinioService minioService;

    public Pessoa registrarPessoa(Pessoa pessoa) {
        log.info("Registrando nova pessoa: {}", pessoa);
        pessoa.getEnderecos().forEach(enderecoService::criar);
        return pessoaRepository.save(pessoa);
    }

    public Foto adicionarFoto(Long pessoaId, MultipartFile foto) {
        Pessoa pessoa = this
                .buscarPorId(pessoaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));

        String fileName;
        try {
            fileName = minioService.subirImagem(foto, fotoServidoresBucket);
        } catch (Exception e) {
            log.error("Erro ao fazer upload da foto: {}", e.getMessage());
            throw new RuntimeException("Erro ao fazer upload da foto. Por favor, verifique o arquivo ou tentar novamente mais tarde.");
        }

        Foto ft = new Foto(pessoa, fotoServidoresBucket, fileName);

        return fotoRepository.save(ft);
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        log.info("Buscando pessoa com ID: {}", id);
        return pessoaRepository.findById(id);
    }

    public Pessoa atualizarPorId(Long id, Pessoa pessoa) {
        log.info("Atualizando pessoa: {}", pessoa);

        Pessoa pessoaExistente = pessoaRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada"));

        pessoa.setId(pessoaExistente.getId());

        return pessoaRepository.save(pessoa);
    }
}
