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

##### Exemplo
Uma requisição **GET** para a url *BASE_URL/cliente* poderá retornar:
```javascript
200 OK
[
  {
    "documento": 7314935912,
    "email": "andrerfarias@gmail.com",
    "foneDdd": 41,
    "foneNumero": 96740459,
    "id": 1,
    "nome": "André Luiz Rodrigues Farias",
    "tipoDocumento": "cpf"
  }
]
```



