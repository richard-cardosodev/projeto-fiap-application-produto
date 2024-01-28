package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.external.repository.mongo.MongoProdutoRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = MongoProdutoRepository.class)
@EntityScan("br.fiap.projeto.produto.external.repository.mongo")
public class MongoProdutoRepositoryConfiguration {
}
