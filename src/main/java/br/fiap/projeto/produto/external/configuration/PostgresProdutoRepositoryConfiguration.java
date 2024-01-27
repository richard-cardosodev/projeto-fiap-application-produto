package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.external.repository.postgres.PostgreProdutoRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = PostgreProdutoRepository.class)
@EntityScan("br.fiap.projeto.produto.external.repository.jpa.entity")
public class PostgreProdutoRepositoryConfiguration {
}
