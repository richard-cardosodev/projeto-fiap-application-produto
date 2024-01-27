package br.fiap.projeto.produto.adapter.gateway;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.external.repository.mongo.MongoProdutoRepository;
import br.fiap.projeto.produto.external.repository.mongo.collections.ProdutoDocument;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRepositoryAdapterGatewayMongo implements IProdutoRepositoryAdapterGateway {

    private final MongoProdutoRepository mongoProdutoRepository;

    public ProdutoRepositoryAdapterGatewayMongo(MongoProdutoRepository mongoProdutoRepository) {
        this.mongoProdutoRepository = mongoProdutoRepository;
    }

    @Override
    public List<Produto> buscaTodos() {
        List<ProdutoDocument> resultados = mongoProdutoRepository.findAll();
        return resultados.stream().map(ProdutoDocument::toProduto).collect(Collectors.toList());
    }

    @Override
    public Optional<Produto> buscaProduto(String codigo) {
        Optional<ProdutoDocument> produtoEntity = mongoProdutoRepository.findByCodigo(codigo);
        return produtoEntity.map(ProdutoDocument::toProduto);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        Optional<ProdutoDocument> resultadoBusca = mongoProdutoRepository.findByNomeIgnoreCase(nome);
        return resultadoBusca.map(ProdutoDocument::toProduto);
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        List<ProdutoDocument> resultados = mongoProdutoRepository.findByCategoria(categoria);
        return resultados.stream().map(ProdutoDocument::toProduto).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) {
        ProdutoDocument produtoSalvo = mongoProdutoRepository.save(new ProdutoDocument(produto));
        return produtoSalvo.toProduto();
    }

    @Override
    public void removeProduto(String codigo) {
        mongoProdutoRepository.deleteByCodigo(codigo);
    }

    @Override
    public void atualizaProduto(Produto produto) {
        mongoProdutoRepository.save(new ProdutoDocument(produto));
    }
}
