package br.fiap.projeto.produto.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.fiap.projeto.produto.external.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Projeto de pós-graduação (Fiap/Alura) do grupo 2023-Q1-64 - contexto produto",
                "Integrantes: Ademar Terra, Aline Santos, Danilo LR, Mario Sclavo, Richard Cardoso",
                "1.0",
                "",
                null,
                "",
                "",
                Collections.emptyList());
    }
}
