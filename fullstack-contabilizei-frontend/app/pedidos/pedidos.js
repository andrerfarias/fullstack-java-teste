'use strict';
angular.module('fullstack.pedidos', ['ngRoute','ngMaterial'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
  .when('/pedidos', {
    templateUrl: 'pedidos/pedidos.html',
    controller: 'pedidosController'
  })
  .when('/pedido/:pedido_id/produto', {
    templateUrl: 'pedidos/pedido-produto.html',
    controller: 'pedidoProdutoController'
  });
}])
.controller('pedidosController', ['$scope', 'fullstackAPIservice','$mdDialog', '$location', function($scope,fullstackAPIservice,$mdDialog,$location) {
  $scope.pedido;
  $scope.pedidos = [];
  $scope.loader = false;
  $scope.clientes = [];
  getPedidos();

  fullstackAPIservice.getClientes().success(response => {
      $scope.clientes = response;
  }).error(err => {
      console.log(err);
  });

  function getPedidos(){
    fullstackAPIservice.getPedidos().success(response => {
        $scope.pedidos = response;
    }).error(err => {
        $scope.pedidos = [];
    });
  }

  $scope.selecionarClientePedido = function(ev) {
    $mdDialog.show({
      templateUrl: 'pedidos/criar-pedido.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose:true,
      fullscreen: $scope.customFullscreen
    })
    .then(function(answer) {
    });
  };

  $scope.fecharDialogPedido = function() {
    $mdDialog.hide();
  };

  $scope.criarPedido = function (cliente){
    $scope.loader = true;
    fullstackAPIservice.postPedido(cliente.id)
    .success(response => {
      $scope.loader = false;
      $scope.pedido = response;
      $mdDialog.hide();
      $location.path( "/pedido/"+response.id+"/produto");
    }).error(err => {
      $scope.loader = false;
    });
  };

  $scope.cancelarPedido = function(pedidoId){
     fullstackAPIservice.deletePedido(pedidoId)
    .success(response => {
      getPedidos();
    }).error(err => {
      getPedidos();
    });
  }
}])
.controller('pedidoProdutoController', ['$scope', 'fullstackAPIservice','$mdDialog', '$location','$routeParams', function($scope,fullstackAPIservice,$mdDialog,$location,$routeParams) {
  let pedido_id = $routeParams.pedido_id;
  $scope.pedido = {};
  $scope.produtos = [];
  $scope.produtoSelecionado = {};
  getPedido();

  function getPedido(){
    fullstackAPIservice.getPedido(pedido_id).success(response => {
        $scope.pedido = response;
    }).error(err => {
        console.log(err);
    });
  }

  fullstackAPIservice.getProdutos().success(response => {
      $scope.produtos = response;
  }).error(err => {
      console.log(err);
  });

  $scope.adicionarProdutoDialog = function(ev, produto, pedido) {
    $scope.produtoSelecionado = produto;
    $scope.pedidoSelecionado = pedido;
    $mdDialog.show({
      templateUrl: 'pedidos/adicionar-produto.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose:true,
      fullscreen: $scope.customFullscreen,
      locals: {
        options: {produtoSelecionado: $scope.produtoSelecionado, quantidadeProduto: 1, pedidoSelecionado: $scope.pedidoSelecionado}
      },
      controller: ProdutoDialogController
    });

    function ProdutoDialogController($scope, $mdDialog, options) {
      $scope.produtoSelecionado = options.produtoSelecionado;
      $scope.pedidoSelecionado = options.pedidoSelecionado;
      $scope.quantidadeProduto = ((options.quantidadeProduto != null && options.quantidadeProduto > 0) ? options.quantidadeProduto : 1);
      $scope.produtoSelecionado.valorTotal = $scope.produtoSelecionado.valorUnitario * $scope.quantidadeProduto;
      $scope.produtoAdicionado;
      $scope.loader = false;

      $scope.fecharDialogProduto = function() {
        $scope.produtoAdicionado = null;
        $mdDialog.hide();
      }

      $scope.calcularValorTotal = function (quantidade){
        $scope.quantidadeProduto  = ((quantidade != null && quantidade >= 1) ? quantidade : 1);
        $scope.produtoSelecionado.valorTotal = $scope.produtoSelecionado.valorUnitario * $scope.quantidadeProduto;
      }

      $scope.adicionarProduto = function (produtoId, pedidoId, quantidade){
        let options = {
          produtoId: produtoId,
          pedidoId: pedidoId,
          quantidade: quantidade
        };
        $scope.loader = true;
        fullstackAPIservice.postProduto(options).success(response => {
          $scope.loader = false;
          $scope.produtoAdicionado = response;
          getPedido();
        }).error( err => {
          $scope.loader = false;
        });
      }

      $scope.redirectPedidos = function(){
        $mdDialog.hide();
        $location.path("/pedidos");
      }
    }
  };

  $scope.removerProduto = function(pedidoProdutoId){
    fullstackAPIservice.deleteProduto(pedidoProdutoId).then(response => {
      getPedido();
    });
  };
}]);