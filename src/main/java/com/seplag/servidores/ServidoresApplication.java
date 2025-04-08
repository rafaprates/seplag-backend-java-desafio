package com.seplag.servidores;

import com.seplag.servidores.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ServidoresApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServidoresApplication.class, args);
    }

}
