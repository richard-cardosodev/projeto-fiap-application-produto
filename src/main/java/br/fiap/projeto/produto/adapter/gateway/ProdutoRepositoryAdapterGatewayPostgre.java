package br.fiap.projeto.produto.adapter.gateway;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.external.repository.jpa.entity.ProdutoEntity;
import br.fiap.projeto.produto.external.repository.postgres.PostgresProdutoRepository;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRepositoryAdapterGatewayPostgre implements IProdutoRepositoryAdapterGateway {

    private final PostgresProdutoRepository postgresProdutoRepository;

    public ProdutoRepositoryAdapterGatewayPostgre(PostgresProdutoRepository postgresProdutoRepository) {
        this.postgresProdutoRepository = postgresProdutoRepository;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<ProdutoEntity> resultados = postgresProdutoRepository.findAll();
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscaProduto(String codigo) {
        Optional<ProdutoEntity> produtoEntity = postgresProdutoRepository.findByCodigo(codigo);
        return produtoEntity.map(ProdutoEntity::toProduto);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        Optional<ProdutoEntity> resultadoBusca = postgresProdutoRepository.findByNomeIgnoreCase(nome);
        return resultadoBusca.map(ProdutoEntity::toProduto);
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<ProdutoEntity> resultados = postgresProdutoRepository.findByCategoria(categoria);
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) {
        ProdutoEntity produtoSalvo = postgresProdutoRepository.save(new ProdutoEntity(produto));
        return produtoSalvo.toProduto();
    }

    @Override
    public void removeProduto(String codigo) {
        postgresProdutoRepository.deleteByCodigo(codigo);
    }

    @Override
    public void atualizaProduto(Produto produto) {
        postgresProdutoRepository.save(new ProdutoEntity(produto));
    }
}
