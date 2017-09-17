'use strict';

/**
 * @ngdoc directive
 * @name myAppApp.directive:mask
 * @description
 * # mask
 */
angular.module('bluebankApp')
  .directive('mask', function () {
    return {
      restrict: 'A',
      link: function postLink(scope, element, attrs) {
        element.mask(attrs.mask);
      }
    };
  });
