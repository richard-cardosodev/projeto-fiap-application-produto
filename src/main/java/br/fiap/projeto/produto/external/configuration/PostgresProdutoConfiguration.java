package br.fiap.projeto.produto.external.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.fiap.projeto.produto.external.repository")
@EntityScan("br.fiap.projeto.produto.external.repository.entity")
public class PostgresProdutoConfiguration {
}
