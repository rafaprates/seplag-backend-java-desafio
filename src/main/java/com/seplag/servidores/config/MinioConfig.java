package com.seplag.servidores.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${bucket.credentials.username}")
    private String username;

    @Value("${bucket.credentials.password}")
    private String password;

    @Value("${bucket.url}")
    private String bucketUrl;

    @Bean
    public MinioClient minIO() {
        return MinioClient.builder()
                .endpoint(bucketUrl)
                .credentials(username, password)
                .build();
    }

}
