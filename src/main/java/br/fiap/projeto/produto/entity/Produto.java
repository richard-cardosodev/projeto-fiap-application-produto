package br.fiap.projeto.produto.entity;

import br.fiap.projeto.produto.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.produto.entity.enums.CategoriaProduto;

import java.net.MalformedURLException;
import java.net.URL;

public class Produto {

    public static final String CODIGO_NULO = "Código não pode ser nulo!";
    public static final String NOME_NULO = "Nome não pode ser nulo!";
    public static final String DESCRICAO_NULA = "A descrição não pode ser nula!";
    public static final String PRECO_NULO = "Preço não pode ser nulo!";
    public static final String PRECO_MENOR_QUE_ZERO = "Preço deve ser um valor maior que zero!";
    public static final String CATEGORIA_NULA = "Categoria não pode ser nula!";
    public static final String IMAGEM_NAO_E_UMA_URL = "Endereço da imagem não representa uma URL!";
    public static final String TEMPO_DE_PREPARO_NULO = "Tempo de preparo não pode ser nulo!";
    public static final String TEMPO_DE_PREPARO_MENOR_QUE_ZERO = "Tempo de preparo deve ser um valor maior ou igual a zero!";
    private String codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private CategoriaProduto categoria;
    private String imagem;
    private Integer tempoPreparoMin;

    public Produto(String nome, String descricao, Double preco, CategoriaProduto categoria, String imagem,
            Integer tempoPreparoMin) throws EntradaInvalidaException {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
        validaProdutoSemCodigo();
    }

    public Produto(String codigo, String nome, String descricao, Double preco, CategoriaProduto categoria,
            String imagem, Integer tempoPreparoMin) throws EntradaInvalidaException {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
        this.tempoPreparoMin = tempoPreparoMin;
        validaProdutoComCodigo();
    }

    private void validaProdutoSemCodigo() throws EntradaInvalidaException {
        validaNome();
        validaDescricao();
        validaPreco();
        validaCategoria();
        validaImagem();
        validaTempoPreparo();
    }

    private void validaProdutoComCodigo() throws EntradaInvalidaException {
        validaCodigo();
        validaNome();
        validaDescricao();
        validaPreco();
        validaCategoria();
        validaImagem();
        validaTempoPreparo();
    }

    private void validaCodigo() throws EntradaInvalidaException {
        if(codigo.equals(null) || codigo.isEmpty()) {
            throw new EntradaInvalidaException(CODIGO_NULO);
        }
    }

    private void validaNome() throws EntradaInvalidaException {
        if(nome.equals(null) || nome.isEmpty()) {
            throw new EntradaInvalidaException(NOME_NULO);
        }
    }

    private void validaDescricao() throws EntradaInvalidaException {
        if(descricao.equals(null) || descricao.isEmpty()) {
            throw new EntradaInvalidaException(DESCRICAO_NULA);
        }
    }

    private void validaPreco() throws EntradaInvalidaException {
        if(preco == null) {
            throw new EntradaInvalidaException(PRECO_NULO);
        }
        if(preco <= 0) {
            throw new EntradaInvalidaException(PRECO_MENOR_QUE_ZERO);
        }
    }

    private void validaCategoria() throws EntradaInvalidaException {
        if(categoria.equals(null)) {
            throw new EntradaInvalidaException(CATEGORIA_NULA);
        }
    }

    private void validaImagem() throws EntradaInvalidaException {
        if(imagem != null) {
            try {
                URL url = new URL(imagem);
            } catch (MalformedURLException e){
                throw new EntradaInvalidaException(IMAGEM_NAO_E_UMA_URL);
            }
        }
    }

    private void validaTempoPreparo() throws EntradaInvalidaException {
        if(tempoPreparoMin == null) {
            throw new EntradaInvalidaException(TEMPO_DE_PREPARO_NULO);
        }
        if (tempoPreparoMin < 0) {
            throw new EntradaInvalidaException(TEMPO_DE_PREPARO_MENOR_QUE_ZERO);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public Integer getTempoPreparoMin() {
        return tempoPreparoMin;
    }
}
