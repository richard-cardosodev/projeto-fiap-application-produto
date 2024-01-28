package br.fiap.projeto.produto.usecase;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.usecase.exception.ProdutoDuplicadoException;
import br.fiap.projeto.produto.usecase.exception.ProdutoNaoEncontradoException;
import br.fiap.projeto.produto.usecase.port.IGestaoProdutoUseCase;
import br.fiap.projeto.produto.usecase.port.IProdutoRepositoryAdapterGateway;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GestaoProdutoUseCase implements IGestaoProdutoUseCase {

    public static final String PRODUTO_NULO = "Entrada inválida! Produto não deve ser nulo!";
    public static final String PRODUTO_JA_CADASTRADO = "O produto não pode ter o mesmo nome de um já cadastrado!";

    private final IProdutoRepositoryAdapterGateway produtoAdapterGateway;

    public GestaoProdutoUseCase(IProdutoRepositoryAdapterGateway produtoAdapterGateway) {
        this.produtoAdapterGateway = produtoAdapterGateway;
    }

    @Override
    public List<Produto> buscaTodos() {
        return produtoAdapterGateway.buscaTodos();
    }

    @Override
    public Produto buscaProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produto = produtoAdapterGateway.buscaProduto(codigo);
        return produto.orElseThrow(ProdutoNaoEncontradoException::new);
    }

    @Override
    public List<Produto> buscaProdutosPorCategoria(CategoriaProduto categoria) {
        return produtoAdapterGateway.buscaProdutosPorCategoria(categoria);
    }

    @Override
    public List<String> getCategoriasDeProdutos() {
        return Arrays.stream(CategoriaProduto.values()).map(c -> c.name()).collect(Collectors.toList());
    }

    @Override
    public Produto criaProduto(Produto produto) throws EntradaInvalidaException, ProdutoDuplicadoException {
        if (produto == null) {
            throw new EntradaInvalidaException(PRODUTO_NULO);
        }
        Optional<Produto> produtoExistente = produtoAdapterGateway.buscaProdutoPorNome(produto.getNome());
        if(produtoExistente.isPresent()){
            throw new ProdutoDuplicadoException(PRODUTO_JA_CADASTRADO);
        }
        Produto newProduto = new Produto(UUID.randomUUID().toString(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getTempoPreparoMin());
        return produtoAdapterGateway.criaProduto(newProduto);
    }

    @Override
    public Void removeProduto(String codigo) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoRecuperado = produtoAdapterGateway.buscaProduto(codigo);
        if(!produtoRecuperado.isPresent()){
            throw new ProdutoNaoEncontradoException();
        }
        produtoAdapterGateway.removeProduto(codigo);
        return null;
    }

    @Override
    public void atualizaProduto(String codigo, Produto produto)
            throws ProdutoNaoEncontradoException, EntradaInvalidaException {
        Optional<Produto> produtoRecuperado = produtoAdapterGateway.buscaProduto(codigo);
        if(!produtoRecuperado.isPresent()){
            throw new ProdutoNaoEncontradoException();
        }
        produtoAdapterGateway.atualizaProduto(new Produto(codigo, produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getTempoPreparoMin()));
    }
}
