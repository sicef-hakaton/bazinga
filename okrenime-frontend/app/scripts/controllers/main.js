'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('MainCtrl', function($scope, Cards) {
    Cards.getAll().then(function(r) {
      $scope.cards = r.data;
    });

  });
