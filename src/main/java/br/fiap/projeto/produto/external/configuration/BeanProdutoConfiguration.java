package br.fiap.projeto.produto.external.configuration;

import br.fiap.projeto.produto.adapter.controller.ProdutoRestAdapterController;
import br.fiap.projeto.produto.adapter.controller.port.IProdutoRestAdapterController;
import br.fiap.projeto.produto.adapter.gateway.ProdutoRepositoryAdapterGateway;
import br.fiap.projeto.produto.external.repository.postgres.SpringProdutoRepository;
import br.fiap.projeto.produto.usecase.GestaoProdutoUseCase;
import br.fiap.projeto.produto.usecase.port.IGestaoProdutoUseCase;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProdutoConfiguration {
    @Bean
    IGestaoProdutoUseCase produtoUseCase(IProdutoRepositoryAdapterGateway produtoAdapterGateway) {
        return new GestaoProdutoUseCase(produtoAdapterGateway);
    }

    @Bean
    IProdutoRepositoryAdapterGateway produtoAdapterGateway(SpringProdutoRepository springProdutoRepository) {
        return new ProdutoRepositoryAdapterGateway(springProdutoRepository);
    }

    @Bean
    IProdutoRestAdapterController produtoAdapterController(IGestaoProdutoUseCase produtoUseCase) {
        return new ProdutoRestAdapterController(produtoUseCase);
    }
}
