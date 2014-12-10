'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('LoginCtrl', function($scope, AuthenticationService, $location, FlashService) {
    $scope.login = function() {
      AuthenticationService.login($scope.credentials).success(function() {
        $location.path('/home');
      });
    };

    $scope.register = function() {
      AuthenticationService.register($scope.credentials).success(function() {
        $scope.flip = false;
      }).error(function() {
        FlashService.show('Invalid register data');
      });
    };
  });
