package br.fiap.projeto.produto.external.repository.postgres;

import br.fiap.projeto.produto.entity.enums.CategoriaProduto;
import br.fiap.projeto.produto.external.repository.jpa.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostgresProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findByCodigo(String codigo);

    Optional<ProdutoEntity> findByNomeIgnoreCase(String nome);

    List<ProdutoEntity> findByCategoria(CategoriaProduto categoria);

    void deleteByCodigo(String codigo);
}
