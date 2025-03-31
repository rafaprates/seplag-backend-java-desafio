package com.seplag.servidores.compartilhado;

import com.seplag.servidores.security.entities.Role;
import com.seplag.servidores.security.entities.Usuario;
import com.seplag.servidores.security.services.RoleService;
import com.seplag.servidores.security.services.UserDetailsService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartupService {

    @Value("${bucket.buckets.fotos-servidores.nome}")
    private String fotoBucketNome;

    private final UserDetailsService userDetailsService;
    private final RoleService roleService;
    private final MinioClient minioClient;

    @PostConstruct
    public void onStartup() {
        log.info("Rodando rotina de inicialização do sistema");

        criarUsuarioPadrao();
        criarBucketMinio();
    }

    private void criarUsuarioPadrao() {
        log.info("Criando usuário padrão do sistema");

        Role rolePadrao = new Role("ROLE_USER");
        roleService.create(rolePadrao);

        Usuario usuarioPadrao = new Usuario(
                "seplag",
                "8xON{d]7;B4i",
                Set.of(rolePadrao)
        );

        userDetailsService.create(usuarioPadrao);
    }

    @SneakyThrows
    private void criarBucketMinio() {
        log.info("Criando bucket {} para armazenamento de fotos dos Servidores", fotoBucketNome);
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(fotoBucketNome).build());

        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(fotoBucketNome).build());
        } else {
            log.info("Bucket {} já existe", fotoBucketNome);
        }
    }

}
