# language: pt
Funcionalidade: Produto

  Cenário: Criar um novo produto
    Quando submeter um novo produto
    Então o produto é registrado com sucesso

  Cenário: Alterar um produto existente
    Dado que um produto já foi registrado
    Quando requisitar a alteração do produto
    Então o produto é alterado com sucesso

  Cenário: Excluir um produto existente
    Dado que um produto já foi registrado
    Quando requisitar a exclusão do produto
    Então o produto é excluido com sucesso

  Cenário: Busca um produto existente
    Dado que um produto já foi registrado
    Quando requisitar a busca do produto
    Então o produto é exibido com sucesso

  Cenário: Lista produtos existentes
    Dado que um produto já foi registrado
    Quando requisitar a lista de produtos
    Então os produtos são exibidos com sucesso

  Cenário: Busca de produtos por categoria
    Dado que um produto já foi registrado na categoria buscada
    Quando requisitar a busca por produtos da categoria
    Então os produtos são exibidos com sucesso

  Cenário: Buscar todas as categorias de produtos
    Dado que lista de categorias exista
    Quando requisitar a sua listagem
    Então as categorias são exibidas

