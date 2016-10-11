angular.module('fullstack.services', []).
  factory('fullstackAPIservice', function($http) {
    var FULLSTACK_SERVICE_API = 'http://localhost:8084/fullstack';
    var fullstackAPI = {};
    
    fullstackAPI.getPedido = function(pedidoId) {
      let response = $http({
        method: 'GET', 
        url: `${FULLSTACK_SERVICE_API}/pedido/${pedidoId}`
      });
      return response;
    }

    fullstackAPI.getPedidos = function() {
      let pedidos = $http({
        method: 'GET', 
        url: `${FULLSTACK_SERVICE_API}/pedido`
      });
      return pedidos;
    }
    /**
     * 
     */
    fullstackAPI.postPedido = function(clienteId) {
      let pedidos = $http({
        method: 'POST', 
        url: `${FULLSTACK_SERVICE_API}/pedido`,
        data: { cliente_id: clienteId}
      });
      return pedidos;
    }

    fullstackAPI.getClientes = function() {
      return $http({
        method: 'GET', 
        url: `${FULLSTACK_SERVICE_API}/cliente`
      });
    }

    fullstackAPI.getProdutos = function() {
      let pedidos = $http({
        method: 'GET', 
        url: `${FULLSTACK_SERVICE_API}/produto`
      });
      return pedidos;
    }

    fullstackAPI.postProduto = function(options) {
      const pedidoId = options.pedidoId;
      const produtoId = options.produtoId;
      const quantidade = options.quantidade || 1;
      let pedidos = $http({
        method: 'POST', 
        url: `${FULLSTACK_SERVICE_API}/pedido/${pedidoId}/produto`,
        data: { produto_id: produtoId, quantidade: quantidade}
      });
      return pedidos;
    }

    fullstackAPI.deleteProduto = function(pedidoProdutoId) {
      let pedidos = $http({
        method: 'DELETE', 
        url: `${FULLSTACK_SERVICE_API}/pedido/produto/${pedidoProdutoId}`
      });
      return pedidos;
    }

    fullstackAPI.deletePedido = function(pedidoId) {
      let pedidos = $http({
        method: 'DELETE', 
        url: `${FULLSTACK_SERVICE_API}/pedido/${pedidoId}`
      });
      return pedidos;
    }

    return fullstackAPI;
  });