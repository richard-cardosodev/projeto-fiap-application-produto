package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.adapter.gateway.ProdutoRepositoryAdapterGatewayPostgre;
import br.fiap.projeto.produto.external.repository.postgres.PostgreProdutoRepository;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgre")
public class PostgreBeanConfiguration {

    @Bean
    IProdutoRepositoryAdapterGateway produtoAdapterGateway(PostgreProdutoRepository postgreProdutoRepository) {
        return new ProdutoRepositoryAdapterGatewayPostgre(postgreProdutoRepository);
    }
}
