package br.fiap.projeto.produto.adapter.controller.port;

import br.fiap.projeto.produto.adapter.controller.rest.request.ProdutoDTORequest;
import br.fiap.projeto.produto.adapter.controller.rest.response.ProdutoDTOResponse;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.usecase.exception.ProdutoDuplicadoException;
import br.fiap.projeto.produto.usecase.exception.ProdutoNaoEncontradoException;

import java.util.List;

public interface IProdutoRestAdapterController {
    public List<ProdutoDTOResponse> buscaTodos();

    public ProdutoDTOResponse buscaProduto(String codigo) throws ProdutoNaoEncontradoException;

    public List<ProdutoDTOResponse> buscaProdutosPorCategoria(CategoriaProduto categoria);

    public ProdutoDTOResponse criaProduto(ProdutoDTORequest produtoDTORequest) throws EntradaInvalidaException, ProdutoDuplicadoException;

    public void removeProduto(String codigo) throws ProdutoNaoEncontradoException;

    public void atualizaProduto(String codigo, ProdutoDTORequest produtoDTO)
            throws ProdutoNaoEncontradoException, EntradaInvalidaException;

    public List<String> getCategoriasDeProdutos();
}
