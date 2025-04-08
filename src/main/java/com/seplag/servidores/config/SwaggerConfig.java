package com.seplag.servidores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Contact contact = new Contact()
                .name("Rafael Bruno Prates Barbosa Cardoso")
                .email("rafaelbprates@gmail.com");


        SecurityScheme bearerAuthScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(new Info()
                        .title("Servidores API")
                        .version("1.0")
                        .description("Projeto criado como desafio para a vaga de Desenvolvedor Back-end Java Pleno para a Seplag MT")
                        .contact(contact))
                .addSecurityItem(securityRequirement)
                .schemaRequirement("bearerAuth", bearerAuthScheme);
    }
}
