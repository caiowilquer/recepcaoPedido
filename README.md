Pedido API
Descrição
A API de Pedidos é uma solução para gerenciar pedidos de clientes, permitindo criar, listar e consultar pedidos. Desenvolvida em Java com um framework ORM e banco de dados MySQL.

Estrutura do Banco de Dados
Para criar o banco de dados necessário para a API, execute o seguinte comando SQL:

CREATE DATABASE pedido_api;

Endpoints da API

Postman URLs

Criar Pedido:
Método: POST
URL: http://localhost:8080/api/processar

Consultar Pedido por ID:
Método: GET
URL: http://localhost:8080/api/pedido/{id}

Listar Todos os Pedidos:
Método: GET
URL: http://localhost:8080/api/pedidos
Exemplos de Dados
JSON para uma Lista de Pedidos
Para listar vários pedidos, utilize o seguinte formato JSON:


Para criar uma lista de pedidos
{
  "pedidos": [
    {
      "dataCadastro": "04/08/2024",
      "nomeProduto": "Produto A",
      "valorUnitario": 100.0,
      "quantidade": 5,
      "cliente": {
        "codigo": 1,
        "nome": "Caio"
      }
    },
    {
      "dataCadastro": "06/08/2024",
      "nomeProduto": "Produto B",
      "valorUnitario": 200.0,
      "quantidade": 3,
      "cliente": {
        "codigo": 2,
        "nome": "Wilquer"
      }
    }
  ]
}
JSON para um Pedido
Para criar um único pedido, utilize o seguinte formato JSON:
    {
      "dataCadastro": "06/08/2024",
      "nomeProduto": "Produto B",
      "valorUnitario": 200.0,
      "quantidade": 3,
      "cliente": {
        "codigo": 2,
        "nome": "Wilquer"
      }
    }
