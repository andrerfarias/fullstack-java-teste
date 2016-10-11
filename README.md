# Visão Geral

A aplicação consiste em um sistema de cadastro de pedidos, cada pedido é composto por um cliente e um ou mais produtos.
Cada ação do sistema está disponibilizada através de serviços **REST** (que serão explicados mais pra frente) onde estão aplicadas as regras de negócio, estes serviços são consumidos por uma outra aplicação **SPA** desenvolvida em **Angular 1.x** responsável pela interação com o usuário.

# Back-End 

Para a criação do Back-end foi a escolhida a *Opção 2* onde foram aplicadas as tecnologias:
* Aplicação Pura JAVA EE
* Maven para controle de dependências
* JAX-RS para servir a API RESTful
* Biblioteca Genson para realizar o Parse dos objetos JSON
* Banco de dados MySQL pela facilidade de configuração

### Serviços


