package br.fiap.projeto.produto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.fiap.projeto.produto.entity.Produto;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;

class ProdutoValidacoesTest {

    @Test
    void criaProdutoComParametrosValidosComChave() throws EntradaInvalidaException {
        assertDoesNotThrow(
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10));
    }

    @Test
    void criaProdutoSemChave() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto(null, "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoSemNome() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", null, "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoSemDescricao() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", null, 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoSemPreco() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", null,
                        CategoriaProduto.LANCHE,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoSemCategoria() throws EntradaInvalidaException {

        assertThrows(
                NullPointerException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        null,
                        "imagem", 10),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoSemTempoPreparoMinimo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", null),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoComTempoPreparoMinimoZero() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", 0),
                "Mensagem de erro");
    }

    @Test
    void criaProdutoComTempoPreparoMinimoNegativo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Produto("123", "Hamburguer", "Lanche para comer bem bão", 20.52,
                        CategoriaProduto.LANCHE,
                        "imagem", -10),
                "Mensagem de erro");
    }

}
