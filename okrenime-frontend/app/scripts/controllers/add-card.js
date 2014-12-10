'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:AddCardCtrl
 * @description
 * # AddCardCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('AddCardCtrl', function($scope, Cards, EducationalUnit, $location) {
    EducationalUnit.getAll()
      .then(function(resp) {
        $scope.educationalUnits = resp;
      });
    $scope.selectedFaculty = {};
    $scope.subject = {};
    $scope.addCard = function(newCard) {
      console.log($scope.subject);
      Cards.addCard($scope.subject.selected.id, newCard)
        .then(function() {
          $location.path('/home');
        });
    }
  });
