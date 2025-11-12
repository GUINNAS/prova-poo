package com.flavindopneu.escola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Escola API")
                        .version("v1")
                        .description("API para gerenciar alunos e cursos")
                        .contact(new Contact().name("Equipe Escola").email("suporte@escola.local"))
                        .license(new License().name("MIT")));
    }
}
