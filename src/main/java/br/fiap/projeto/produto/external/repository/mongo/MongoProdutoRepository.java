package br.fiap.projeto.produto.external.repository.mongo;

import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.external.repository.mongo.collections.ProdutoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoProdutoRepository extends MongoRepository<ProdutoDocument, String> {
    Optional<ProdutoDocument> findByCodigo(String codigo);

    Optional<ProdutoDocument> findByNomeIgnoreCase(String nome);

    List<ProdutoDocument> findByCategoria(CategoriaProduto categoria);

    void deleteByCodigo(String codigo);
}
