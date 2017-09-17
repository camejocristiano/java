'use strict';

var app = angular.module('demo', ['ngSanitize', 'ui.select']);

/**
 * AngularJS default filter with the following expression:
 * "person in people | filter: {cardapio: $select.search, cod: $select.search}"
 * performs a AND between 'cardapio: $select.search' and 'cod: $select.search'.
 * We want to perform a OR.
 */
app.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];

    if (angular.isArray(items)) {
      var keys = Object.keys(props);
        
      items.forEach(function(item) {
        var itemMatches = false;

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
      // Let the output be the input untouched
      out = items;
    }

    return out;
  };
});

app.controller('DemoCtrl', function($scope, $http, $timeout, $interval) {
  $scope.disabled = undefined;
  $scope.searchEnabled = undefined;

  $scope.setInputFocus = function (){
    $scope.$broadcast('UiSelectDemo1');
  };

  $scope.enable = function() {
    $scope.disabled = false;
  };

  $scope.disable = function() {
    $scope.disabled = true;
  };

  $scope.enableSearch = function() {
    $scope.searchEnabled = true;
  };

  $scope.disableSearch = function() {
    $scope.searchEnabled = false;
  };

  $scope.clear = function() {
    $scope.person.selected = undefined;
    $scope.address.selected = undefined;
    $scope.garcom.selected = undefined;
  };

  $scope.someGroupFn = function (item){

    if (item.cardapio[0] >= 'A' && item.cardapio[0] <= 'M')
        return 'From A - M';

    if (item.cardapio[0] >= 'N' && item.cardapio[0] <= 'Z')
        return 'From N - Z';

  };

  $scope.firstLetterGroupFn = function (item){
      return item.cardapio[0];
  };

  $scope.reverseOrderFilterFn = function(groups) {
    return groups.reverse();
  };

  $scope.personAsync = {selected : "wladimir@email.com"};
  $scope.peopleAsync = [];

  $timeout(function(){
   $scope.peopleAsync = [
        { cardapio: 'Baiao de 2',      email: '',      cod: 1, garcom: '' },
        { cardapio: 'Galinha Caipira',    email: '',    cod: 2, garcom: '' },
        { cardapio: 'Costela de Boi', email: '', cod: 3, garcom: '' },
        { cardapio: 'Feijoada',    email: '',    cod: 4, garcom: '' },
        { cardapio: 'Banana Split',  email: '',  cod: 5, garcom: '' },
        { cardapio: 'Cachaça',  email: '',  cod: 6, garcom: '' },
        { cardapio: 'Coca-cola',    email: '',    cod: 7, garcom: '' },
        { cardapio: 'Torresmo',   email: '',   cod: 8, garcom: '' },
        { cardapio: 'Calabresa com ovo',   email: '',   cod: 9, garcom: '' },
        { cardapio: 'Carne de sol',   email: '',    cod: 10, garcom: '' }
      ];
  },3000);

  $scope.counter = 0;
  $scope.someFunction = function (item, model){
    $scope.counter++;
    $scope.eventResult = {item: item, model: model};
  };

  $scope.removed = function (item, model) {
    $scope.lastRemoved = {
        item: item,
        model: model
    };
  };

  $scope.tagTransform = function (newTag) {
    var item = {
        cardapio: newTag,
        email: newTag.toLowerCase()+'@email.com',
        cod: 'unknown',
        garcom: 'unknown'
    };

    return item;
  };

  $scope.peopleObj = {
    '1' : { cardapio: 'Adam',      email: 'adam@email.com',      cod: 12, garcom: 'United States' },
    '2' : { cardapio: 'Amalie',    email: 'amalie@email.com',    cod: 12, garcom: 'Argentina' },
    '3' : { cardapio: 'Estefanía', email: 'estefania@email.com', cod: 21, garcom: 'Argentina' },
    '4' : { cardapio: 'Adrian',    email: 'adrian@email.com',    cod: 21, garcom: 'Ecuador' },
    '5' : { cardapio: 'Wladimir',  email: 'wladimir@email.com',  cod: 30, garcom: 'Ecuador' },
    '6' : { cardapio: 'Samantha',  email: 'samantha@email.com',  cod: 30, garcom: 'United States' },
    '7' : { cardapio: 'Nicole',    email: 'nicole@email.com',    cod: 43, garcom: 'Colombia' },
    '8' : { cardapio: 'Natasha',   email: 'natasha@email.com',   cod: 54, garcom: 'Ecuador' },
    '9' : { cardapio: 'Michael',   email: 'michael@email.com',   cod: 15, garcom: 'Colombia' },
    '10' : { cardapio: 'Nicolás',   email: 'nicolas@email.com',    cod: 43, garcom: 'Colombia' }
  };

  $scope.person = {};

  $scope.person.selectedValue = $scope.peopleObj[3];
  $scope.person.selectedSingle = 'Samantha';
  $scope.person.selectedSingleKey = '5';

  $scope.people = [
        { cardapio: 'Baiao de 2',      email: '',      cod: 1, garcom: '' },
        { cardapio: 'Galinha Caipira',    email: '',    cod: 2, garcom: '' },
        { cardapio: 'Costela de Boi', email: '', cod: 3, garcom: '' },
        { cardapio: 'Feijoada',    email: '',    cod: 4, garcom: '' },
        { cardapio: 'Banana Split',  email: '',  cod: 5, garcom: '' },
        { cardapio: 'Cachaça',  email: '',  cod: 6, garcom: '' },
        { cardapio: 'Coca-cola',    email: '',    cod: 7, garcom: '' },
        { cardapio: 'Torresmo',   email: '',   cod: 8, garcom: '' },
        { cardapio: 'Calabresa com ovo',   email: '',   cod: 9, garcom: '' },
        { cardapio: 'Carne de sol',   email: '',    cod: 10, garcom: '' }
  ];

  $scope.availableColors = ['Red','Green','Blue','Yellow','Mcodnta','Maroon','Umbra','Turquoise'];

  $scope.singleDemo = {};
  $scope.singleDemo.color = '';
  $scope.multipleDemo = {};
  $scope.multipleDemo.colors = ['Blue','Red'];
  $scope.multipleDemo.colors2 = ['Blue','Red'];
  $scope.multipleDemo.selectedPeople = [$scope.people[5], $scope.people[4]];
  $scope.multipleDemo.selectedPeople2 = $scope.multipleDemo.selectedPeople;
  $scope.multipleDemo.selectedPeopleWithGroupBy = [$scope.people[8], $scope.people[6]];
  $scope.multipleDemo.selectedPeopleSimple = ['samantha@email.com','wladimir@email.com'];

  $scope.appendToBodyDemo = {
    remainingToggleTime: 0,
    present: true,
    startToggleTimer: function() {
      var scope = $scope.appendToBodyDemo;
      var promise = $interval(function() {
        if (scope.remainingTime < 1000) {
          $interval.cancel(promise);
          scope.present = !scope.present;
          scope.remainingTime = 0;
        } else {
          scope.remainingTime -= 1000;
        }
      }, 1000);
      scope.remainingTime = 3000;
    }
  };



  $scope.addPerson = function(item, model){
    if(item.hasOwnProperty('isTag')) {
      delete item.isTag;
      $scope.people.push(item);
    }
  }

 


    // Manipulações dos itens do pedido
    // Variaveis coleadas dos cardapios
    $scope.itensPedido = [
      
    ];
    $scope.adicionarItem = function(selected){
        if(selected.hasOwnProperty('name')) {
          delete $scope.selected;        
        } else {
          $scope.itensPedido.push(selected);
          delete $scope.selected;
        }
    };
    // / Manipulações dos itens do pedido
});
