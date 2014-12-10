'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('RegisterCtrl', function($scope, AuthenticationService, $location, FlashService) {
    $scope.register = function() {
      AuthenticationService.register($scope.credentials).success(function() {
        $location.path('/login');
      }).error(function() {
        FlashService.show('Invalid register data');
      });
    };
  });
