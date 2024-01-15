Feature: Produto

  Scenario: Criar produto
    Given dado que eu recebi os dados para preenchimento de um produto
    When irei preencher um registro de produto no banco de dados
    Then recebo a resposta com um registro de produto com o código de identificação gerado na persistencia

  Scenario: Alterar produto
    Given dado que eu tenho o registro de um produto
    When quando eu receber os dados dos campos que devem ser alterados
    Then efetuo a alteração dos campos no produto
    And retorno um código de sucesso

  Scenario: Remover produto
    Given dado que eu tenho o registro de um produto
    When quando eu receber o código deste produto
    Then efetuo a remoção do produto
    And retorno um código de sucesso

  Scenario: Busca de um produto
    Given dado que eu tenho o registro de um produto
    When quando eu receber o código deste produto
    Then executo a busca do produto
    And retorno os dados do produto

  Scenario: Buscar todos os produtos cadastrados
    Given dado que recebo uma requisição para listar todos os produtos
    Then executo a busca por todos os produtos
    And retorno a listagem dos dados dos produtos encontrados

  Scenario: Busca de produtos por categoria
    Given dado que eu tenho o registro de produtos nesta categoria solicitada
    When quando eu receber a identificação da categoria
    Then executo a busca dos produtos
    And retorno a listagem dos produtos encontrados

  Scenario: Buscar todas as categorias de produtos
    Given dado que recebo uma requisição para listar todas as categorias de produtos
    Then executo a busca por todas as categorias de produtos
    And retorno a listagem das categorias encontradas

