	'use strict';

	// create the module and name it bluebankApp
	var bluebankApp = angular.module('bluebankApp', ['ngRoute']);

	// configure our routes
	bluebankApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider){
		$routeProvider
			.when('/', {
				templateUrl : 'resources/pages/home.html',
				controller  : 'mainController'
			})
			.when('/sobre', {
				templateUrl : 'resources/pages/sobre.html',
				controller  : 'sobreController'
			})
			.when('/contatos', {
				templateUrl : 'resources/pages/contatos.html',
				controller  : 'contatosController'
			})
			.when('/conta', {
				templateUrl : 'resources/pages/contas/conta.html',
				controller  : 'contaController'
			})
			.when('/contas', {
				templateUrl : 'resources/pages/contas/contas.html',
				controller  : 'contasController'
			})
			.when('/sacar', {
				templateUrl : 'resources/pages/movimentacoes/sacar.html',
				controller  : 'sacarController'
			})
			.when('/depositar', {
				templateUrl : 'resources/pages/movimentacoes/depositar.html',
				controller  : 'depositarController'
			})
			.when('/transferir', {
				templateUrl : 'resources/pages/movimentacoes/transferir.html',
				controller  : 'transferirController'
			})

			.otherwise({
		        redirect: '/'
		    });

		// http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
	 }]);

	
	
	bluebankApp.controller('mainController', function($scope) {
		$scope.message = 'Home!';
	});

	bluebankApp.controller('sobreController', function($scope) {
		$scope.message = 'Sobre.';
	});
	
	bluebankApp.controller('contatosController', function($scope) {
		$scope.message = 'Contato.';
	});
	
	
	
	
	bluebankApp.controller('contaController', function($scope, $http) {
		$scope.conta = {};
		$scope.carregarConta = function(conta){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/conta-agencia-numero/"+conta.agencia+"/"+conta.numero).success(function(data){
			    $scope.conta = data;
		    });
	    }	
		$scope.message = 'Conta.';
	});
	
	bluebankApp.controller('contasController', function($scope, $http) {
		$scope.conta = {};
	    var carregarContas = function(){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/contas").success(function(data){
			    $scope.contas = data;
		    });
	    }	
        carregarContas();
		$scope.message = 'contas';
	});

	
	
	bluebankApp.controller('sacarController', function($scope, $http) {
		$scope.conta = {};
		$scope.message = 'Insira os dados para a transacao! Ok!';
		$scope.bootstrap = 'info';
		$scope.carregarConta = function(conta){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/conta-agencia-numero-cpf/"+conta.agencia+"/"+conta.numero+"/"+conta.cpf).success(function(data){
			    $scope.conta = data;
				$scope.message = 'Conta Ativa! Insira o valor para deposito!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Dados Incorretos!';
		    	$scope.bootstrap = 'danger';
		    });
		}
		$scope.sacar = function(id, valor, decimal){
	    	$http.put("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/sacar/"+id+"/"+valor+"/"+decimal).success(function(data){
	    		$scope.message = 'Saque efetuado com sucesso!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Saldo Insuficiente!';
		    	$scope.bootstrap = 'danger';
		    });
	    	delete $scope.conta;
	    	delete $scope.valor;
		}
	});
	
	bluebankApp.controller('depositarController', function($scope, $http) {
		$scope.conta = {};
		$scope.message = 'Insira os dados para a transacao!';
		$scope.bootstrap = 'info';
		$scope.carregarConta = function(conta){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/conta-agencia-numero/"+conta.agencia+"/"+conta.numero).success(function(data){
			    $scope.conta = data;
				$scope.message = 'Conta Ativa! Insira o valor para deposito!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Dados Incorretos!';
		    	$scope.bootstrap = 'danger';
		    });
		}
		$scope.depositar = function(id, valor, decimal){
	    	$http.put("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/depositar/"+id+"/"+valor+"/"+decimal).success(function(data){
	    		$scope.message = 'Deposito efetuado com sucesso!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Algo saiu errado! Tente novamente mais tarde!';
		    	$scope.bootstrap = 'danger';
		    });
	    	delete $scope.conta;
	    	delete $scope.valor;
		}	
	});
	
	bluebankApp.controller('transferirController', function($scope, $http) {
		$scope.message = 'Insira os dados para a transacao! Ok!';
		$scope.bootstrap = 'info';
		$scope.contaOrigem = {};
		$scope.carregarContaOrigem = function(contaOrigem){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/conta-agencia-numero-cpf/"+contaOrigem.agencia+"/"+contaOrigem.numero+"/"+contaOrigem.cpf).success(function(data){
			    $scope.contaOrigem = data;
				$scope.message = 'Conta de Origem Ativa!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Dados Incorretos!';
		    	$scope.bootstrap = 'danger';
		    });
		}
		$scope.contaDestino = {};
		$scope.carregarContaDestino = function(contaDestino){
		    $http.get("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/conta-agencia-numero/"+contaDestino.agencia+"/"+contaDestino.numero).success(function(data){
			    $scope.contaDestino = data;
				$scope.message = 'Conta de Destino Ativa!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Dados Incorretos!';
		    	$scope.bootstrap = 'danger';
		    });
		}
		$scope.transferir = function(origem, destino, valor, decimal){
	    	$http.put("http://bluebankmaster-camejo.rhcloud.com/BlueBankMaster/transferir/"+origem+"/"+destino+"/"+valor+"/"+decimal).success(function(data){
	    		$scope.message = 'Transferencia efetuada com sucesso!';
	    		$scope.bootstrap = 'success';
		    }).error(function(){
		    	$scope.message = 'Saldo Insuficiente!';
		    	$scope.bootstrap = 'danger';
		    });
	    	delete $scope.contaOrigem;
	    	delete $scope.contaDestino;
	    	delete $scope.conta.valor;
	    	delete $scope.conta.decimal;
		}	
	});
	
