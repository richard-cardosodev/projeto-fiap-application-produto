package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.adapter.gateway.ProdutoRepositoryAdapterGatewayPostgre;
import br.fiap.projeto.produto.external.repository.postgres.PostgresProdutoRepository;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgre")
public class PostgresBeanConfiguration {

    @Bean
    IProdutoRepositoryAdapterGateway produtoAdapterGateway(PostgresProdutoRepository postgresProdutoRepository) {
        return new ProdutoRepositoryAdapterGatewayPostgre(postgresProdutoRepository);
    }
}
