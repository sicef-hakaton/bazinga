'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('NavCtrl', function($scope) {
    $scope.doLogout = function() {
      console.log('asdf');
      sessionStorage.removeItem('authenticated');
      window.location.href = '';
    }
  });
