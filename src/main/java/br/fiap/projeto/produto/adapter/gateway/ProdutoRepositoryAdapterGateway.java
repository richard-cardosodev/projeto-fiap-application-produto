package br.fiap.projeto.produto.adapter.gateway;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.external.repository.entity.ProdutoEntity;
import br.fiap.projeto.produto.external.repository.postgres.SpringProdutoRepository;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRepositoryAdapterGateway implements IProdutoRepositoryAdapterGateway {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepositoryAdapterGateway(SpringProdutoRepository springProdutoRepository) {
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<ProdutoEntity> resultados = springProdutoRepository.findAll();
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscaProduto(String codigo) {
        Optional<ProdutoEntity> produtoEntity = springProdutoRepository.findByCodigo(codigo);
        return produtoEntity.map(ProdutoEntity::toProduto);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        Optional<ProdutoEntity> resultadoBusca = springProdutoRepository.findByNomeIgnoreCase(nome);
        return resultadoBusca.map(ProdutoEntity::toProduto);
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<ProdutoEntity> resultados = springProdutoRepository.findByCategoria(categoria);
        return resultados.stream().map(ProdutoEntity::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) {
        ProdutoEntity produtoSalvo = springProdutoRepository.save(new ProdutoEntity(produto));
        return produtoSalvo.toProduto();
    }

    @Override
    public void removeProduto(String codigo) {
        springProdutoRepository.deleteByCodigo(codigo);
    }

    @Override
    public void atualizaProduto(Produto produto) {
        springProdutoRepository.save(new ProdutoEntity(produto));
    }
}
