'use strict';

var app = angular.module('demo', ['ngSanitize', 'ui.select']);

app.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];

    if (angular.isArray(items)) {
      items.forEach(function(item) {
        var itemMatches = false;

        var keys = Object.keys(props);
        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }

        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      out = items;
    }

    return out;
  }
});

app.controller('DemoCtrl', function($scope, $http) {
 
  $scope.cardapio = {};
  var carregarCardapios = function(){
	  $http.get("cardapiocontroller.do?acao=listarjson").success(function(data){
		  $scope.cardapios = data;
	  });
  }	
  carregarCardapios();
  
  $scope.cliente = {};
  var carregarClientes = function(){
	  $http.get("clientecontroller.do?acao=listarjson").success(function(data){
		  $scope.clientes = data;
	  });
  }	
  carregarClientes();

  $scope.adicionarItem = function(itemSelecionadoJS) {
	 $scope.itensSelecionados.push(angular.copy(itemSelecionadoJS));
	 $scope.somar(itemSelecionadoJS.valor);
	 delete $scope.itemSelecionadoJS;
  };
  
  $scope.apagarItem = function(itemSelecionado) {
	  var pos = $scope.itensSelecionados.indexOf(itemSelecionado);
	  $scope.itensSelecionados.splice(pos, 1);
	  $scope.subtrair(itemSelecionado.valor);
	  delete $scope.itemSelecionado;
  };
  
  $scope.itensSelecionados = [];
  $scope.total = 0;
  $scope.somar = function(valorSomar) {
	  $scope.total += parseFloat(valorSomar);
  }
  
  $scope.subtrair = function(valorSubtrair) {
	  $scope.total -= parseFloat(valorSubtrair);
  }
  
  $scope.troco = 0;
  $scope.diferenca = 0;
  $scope.fazerTroco = function(troco) {
      $scope.diferenca = ($scope.total - troco)*-1;
    };

});
