'use strict';

/**
 * @ngdoc function
 * @name okreniMeApp.controller:UserCardsCtrl
 * @description
 * # UserCardsCtrl
 * Controller of the okreniMeApp
 */
angular.module('okreniMeApp')
  .controller('UserCardsCtrl', function($scope, EducationalUnit, Cards, $filter, $timeout) {
    $scope.selectedFaculty = {};

    EducationalUnit.getAll()
      .then(function(resp) {
        $scope.educationalUnits = resp;
      });

    $scope.subject = {};
    $scope.selectSubject = function(subject) {
      if (!subject) {
        return;
      }
      Cards.getBySubject(subject.id)
        .then(function(resp) {
          $scope.cards = resp;
        });
    };

    $scope.flipCard = function(card) {
      console.log(card);
      card.flipped = !card.flipped;
    }

    $scope.getSelectedSubject = function() {
      return $scope.subject.selected.name;
    }

    $scope.goToNextCard = function(index) {
      $scope.filteredCards[index].show = true;
      $scope.filteredCards[index].animateIn = true;
      $timeout(function() {
        $scope.filteredCards[index].animateIn = false;
      }, 1001);
    }

    $scope.play = function() {
      $scope.showBackdrop = true;
      $scope.filteredCards = _.map(angular.copy($filter('filter')($scope.cards, $scope.searchCardText)), function(c) {
        c.flipped = false;
        return c;
      });
      $scope.currentCard = 0;
      $scope.goToNextCard($scope.currentCard);
    }

    $scope.stop = function() {
      $scope.showBackdrop = false;
    }

    $scope.toggleHard = function(card) {
      if (card.watch) {
        Cards.unwatchCard(card)
          .then(function() {
            card.watch = !card.watch;
          });
      } else {
        Cards.watchCard(card)
          .then(function() {
            card.watch = !card.watch;
          });
      }
    }

    $scope.next = function() {
      if ($scope.filteredCards.length - 1 == $scope.currentCard) {
        $scope.closeCards();
        return;
      }

      $scope.filteredCards[$scope.currentCard].animateOut = true;
      $scope.currentCard++;
      $scope.goToNextCard($scope.currentCard);
    }

    $scope.closeCards = function() {
      $scope.filteredCards[$scope.currentCard].animateOut = true;
      $scope.showBackdrop = false;
      $scope.filteredCards = [];
      $scope.currentCard = 0;

    }

    $scope.reportCard = function(card) {
      Cards.reportCard(card)
        .then(function() {
          card.didIReport = true;
        });
    }

    $scope.rateCard = function(card, rate) {
      Cards.rateCard(card, rate)
        .then(function() {
          $scope.successRating = true;
        });
    }

  });
