# Visão Geral

A aplicação consiste em um sistema de cadastro de pedidos, cada pedido é composto por um cliente e um ou mais produtos.
Cada ação do sistema está disponibilizada através de serviços **REST** (que serão explicados mais pra frente) onde estão aplicadas as regras de negócio, estes serviços são consumidos por uma outra aplicação **SPA** desenvolvida em **Angular 1.x** responsável pela interação com o usuário.

# Back-End 

Para a criação do Back-end foi a escolhida a *Opção 2* onde foram aplicadas as tecnologias:
* Aplicação Pura JAVA EE
* Maven para controle de dependências
* JAX-RS para servir a API RESTful
* Biblioteca Genson para realizar o Parse dos objetos JSON
* Apache Tomcat 8.0.27.0
* Banco de dados MySQL pela facilidade de configuração

## Serviços

Os serviços estão disponíveis de acordo com sua função, cada serviço retornará um **HTTP STATUS CODE** indicando o status do processamento da requisição junto de um **JSON PAYLOAD** quando for necessário retorno de resultados, os métodos HTTP utilizados variam de acordo com a função do serviço, como por exemplo:

* **GET**: Serviços que buscam resultados em um recurso de acordo com os parâmetros passados na URL
* **POST** Serviços que criam novos recursos
* **PUT** Serviços que atualizam recursos já existentes
* **DELETE** Serviços que removem ou cancelam recursos já existentes

Para acessar os serviços basta relalizar uma requisição para o servidor *(Ex: http://localhost:8084)* com URL BASE */fullstack*

### Clientes

#### getClientes

Este serviço irá retornar todos os clientes cadastrados no banco de dados, também podem ser realizados filtros de acordo com alguns parâmetros de busca *(Query Params)*, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **GET**  |  **BASE_URL**/cliente
documento | Query Param | Documento do cliente (CPF ou CNPJ)
email | Query Param | E-mail do cliente
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum registro for encontrado

Uma requisição **GET** para a url *BASE_URL/cliente* poderá retornar:
```javascript
200 OK
[
  {
    "id": xxxxxx,
    "documento": xxxxxxxxxx,
    "email": "xxxxxxxx@xxxxxxx.xxx",
    "foneDdd": xx,
    "foneNumero": xxxxxxxx,
    "nome": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "tipoDocumento": "xxxx" 
  }
]
```

#### getCliente(:id)
Este serviço irá retornar o dados de um cliente específico cadastrados no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **GET**  |  **BASE_URL**/cliente/:id
id | Path Param | ID do cliente solicitado
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum registro for encontrado

Uma requisição **GET** para a url *BASE_URL/cliente/:id* poderá retornar:
```javascript
200 OK
  {
    "id": xxxxxx,
    "documento": xxxxxxxxxx,
    "email": "xxxxxxxx@xxxxxxx.xxx",
    "foneDdd": xx,
    "foneNumero": xxxxxxxx,
    "nome": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "tipoDocumento": "xxxx" 
  }
```

### Produtos

#### getProdutos

Este serviço irá retornar todos os produtos cadastrados no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **GET**  |  **BASE_URL**/produto
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum registro for encontrado

Uma requisição **GET** para a url *BASE_URL/produto* poderá retornar:
```javascript
200 OK
[
  {
    "id": xxxxxx,
    "descricao": "xxxxxxxxxxxxx",
    "nome": "xxxxxxxxxxx",
    "valorUnitario": xxxx.xx
  }
]
```

### Pedidos

#### getPedidos

Este serviço irá retornar todos os pedidos cadastrados e ativos no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **GET**  |  **BASE_URL**/pedido
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum registro for encontrado

Uma requisição **GET** para a url *BASE_URL/pedido* poderá retornar:
```javascript
200 OK
[
  {
    "cliente": {
      "documento": xxxxxxxxxx,
      "email": "xxxxxxxx@xxxx.xxxx",
      "foneDdd": xx,
      "foneNumero": xxxxxxxx,
      "id": xxxxx,
      "nome": "xxxxxxxx",
      "tipoDocumento": "xxxxx"
    },
    "dataEmissao": xxxxxxxxxxxxx,
    "id": xxx,
    "produtos": [
      {
        "dataInclusao": xxxxxxxxxxxxxxx,
        "id": xxx,
        "nome": "xxxxxxx",
        "quantidade": xx,
        "status": "xxxxx",
        "valorTotal": xxxx.xx,
        "valorUnitario": xxxx.xx
      },
      {
        "dataInclusao": xxxxxxxxxxxxxxx,
        "id": xxx,
        "nome": "xxxxxxx",
        "quantidade": xx,
        "status": "xxxxx",
        "valorTotal": xxxx.xx,
        "valorUnitario": xxxx.xx
      }
    ],
    "status": {
      "id": xxx,
      "nome": "xxxx"
    },
    "valorTotal": xxxx.xx
  }
]
```

#### getPedido(:id)

Este serviço irá retornar as informações de um pedido específico no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **GET**  |  **BASE_URL**/pedido/:id
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum registro for encontrado

Uma requisição **GET** para a url *BASE_URL/pedido/:id* poderá retornar:
```javascript
200 OK
  {
    "cliente": {
      "documento": xxxxxxxxxx,
      "email": "xxxxxxxx@xxxx.xxxx",
      "foneDdd": xx,
      "foneNumero": xxxxxxxx,
      "id": xxxxx,
      "nome": "xxxxxxxx",
      "tipoDocumento": "xxxxx"
    },
    "dataEmissao": xxxxxxxxxxxxx,
    "id": xxx,
    "produtos": [
      {
        "dataInclusao": xxxxxxxxxxxxxxx,
        "id": xxx,
        "nome": "xxxxxxx",
        "quantidade": xx,
        "status": "xxxxx",
        "valorTotal": xxxx.xx,
        "valorUnitario": xxxx.xx
      },
      {
        "dataInclusao": xxxxxxxxxxxxxxx,
        "id": xxx,
        "nome": "xxxxxxx",
        "quantidade": xx,
        "status": "xxxxx",
        "valorTotal": xxxx.xx,
        "valorUnitario": xxxx.xx
      }
    ],
    "status": {
      "id": xxx,
      "nome": "xxxx"
    },
    "valorTotal": xxxx.xx
  }
```

#### deletePedido(:id)

Este serviço irá cancelar um pedido específico no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **DELETE**  |  **BASE_URL**/pedido/:id
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum pedido for encontrado

Uma requisição **DELETE** para a url *BASE_URL/pedido/:id* poderá retornar:
```javascript
200 OK
```

#### postPedido()

Este serviço irá incluir um novo pedido no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **POST**  |  **BASE_URL**/pedido
Request | JSON PAYLOAD | ```{ "cliente_id" : id do cliente dono do pedido }```
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum pedido for encontrado

Uma requisição **POST** para a url *BASE_URL/pedido* poderá retornar:
```javascript
201 CREATED

{
  "cliente": {
    "documento": xxxxxxxxxx,
    "email": "xxxxxxxxxxxx@xxxxx.xxx",
    "foneDdd": xx,
    "foneNumero": xxxxxxxx,
    "id": xxxxx,
    "nome": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "tipoDocumento": "xxxx"
  },
  "dataEmissao": xxxxxxxxxxxxxxxxxxxxx,
  "id": xxxx,
  "produtos": null,
  "status": {
    "id": xxxxxx,
    "nome": "xxxxxxxxxxxxxx"
  },
  "valorTotal": xxxx.xx
}

```

#### putPedido(:id)

Este serviço irá atualizar um registro existente no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **PUT**  |  **BASE_URL**/pedido/:id
Request | JSON PAYLOAD | ```{ "cliente_id" : id do cliente dono do pedido }```
Response | HTTP Code | Status 200 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum pedido for encontrado

Uma requisição **PUT** para a url *BASE_URL/pedido/:id* poderá retornar:
```javascript
200 OK

{
  "cliente": {
    "documento": xxxxxxxxxx,
    "email": "xxxxxxxxxxxx@xxxxx.xxx",
    "foneDdd": xx,
    "foneNumero": xxxxxxxx,
    "id": xxxxx,
    "nome": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "tipoDocumento": "xxxx"
  },
  "dataEmissao": xxxxxxxxxxxxxxxxxxxxx,
  "id": xxxx,
  "produtos": null,
  "status": {
    "id": xxxxxx,
    "nome": "xxxxxxxxxxxxxx"
  },
  "valorTotal": xxxx.xx
}

```
#### postPedidoProduto(:id)

Este serviço irá incluir um produto em um pedido existente no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **POST**  |  **BASE_URL**/pedido/:id/produto
Request | JSON PAYLOAD | ```{ "produto_id" : id do produto que deverá ser incluído, "quantidade" : quantidade do produto que será incluída na compra }```
Response | HTTP Code | Status 201 e um PAYLOAD em JSON ou 404 com corpo vazio se nenhum pedido for encontrado

Uma requisição **POST** para a url *BASE_URL/pedido/:id/produto* poderá retornar:
```javascript
201 CREATED
{
  "dataInclusao": xxxxxxxxxxxxxxxxxxxx,
  "id": xxxxx,
  "nome": "xxxxxxxxxxxxxx",
  "quantidade": xxxx,
  "status": "xxxxxx",
  "valorTotal": xxxx.xx,
  "valorUnitario": xxxx.xx
}
```

#### deletePedidoProduto(:pedidoProdutoId)

Este serviço irá incluir um produto em um pedido existente no banco de dados, veja tabela de parâmetros:

Parâmetro de entrada | Tipo | Descrição
------------ | ------------- |-------------
URL | **DELETE**  |  **BASE_URL**/pedido/produto/:pedidoProdutoId
Response | HTTP Code | Status 200 ou 404 com corpo vazio se nenhum pedido for encontrado

Uma requisição **DELETE** para a url *BASE_URL/pedido/produto/:pedidoProdutoId* poderá retornar:
```javascript
200 OK
```


# Front-End 

O Front-End foi criado utilizando as tecnologias propostas pelo teste, o framework utilizado para a criação da interface foi o Angular Material Design (https://material.angularjs.org).

Todas as regras de negócio estão aplicadas no serviço, a interface utilizará somente os retornos para exibição das informações e interação com o usuário.

## Consumo de serviços

Foi criado um componente utilizando o *factory* do Angular para reutilização dos recursos e injeção de dependência nos controllers que irão realizar requisições para os serviços, a configuração da *URL Base* do serviço fica neste arquivo *(services.js)* através da constante *FULLSTACK_SERVICE_API* que se encontra na pasta *app* do projeto.


# Instalação e Configuração

## Banco de Dados
* MySQL versão 5.7.15
* Nome do DB: teste-contabilizei
* Para criar o banco de dados basta executar o [arquivo de importação](/database-import.sql)

## Back-End

## Front-End
