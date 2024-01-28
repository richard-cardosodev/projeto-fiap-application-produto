package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.adapter.gateway.ProdutoRepositoryAdapterGatewayMongo;
import br.fiap.projeto.produto.external.repository.mongo.MongoProdutoRepository;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongo")
public class MongoBeanConfiguration {

    @Bean
    IProdutoRepositoryAdapterGateway produtoAdapterGateway(MongoProdutoRepository mongoProdutoRepository) {
        return new ProdutoRepositoryAdapterGatewayMongo(mongoProdutoRepository);
    }
}
