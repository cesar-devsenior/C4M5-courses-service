package com.devsenior.cdiaz.courses_service.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguration {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("API de gestion de cursos académicos")
                .description("Sistema completo de gestion de cursos para una universidad virtual")
                .version("v1.0")
                .contact(new Contact()
                    .name("Cesar Augusto Diaz")
                    .email("cdiaz@devsenior.com")
                    .url("http://devseniorcode.com"))
                .license(new License()
                    .name("MIT Licence")
                    .url("https://opensource.org/licence/MIT")))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("Entorno de desarrollo"),
                new Server().url("https://api.cursos.devsenior.com").description("Entorno de produccion")
            ));
    }
}
