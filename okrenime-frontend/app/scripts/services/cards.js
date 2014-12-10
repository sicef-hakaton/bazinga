'use strict';

/**
 * @ngdoc service
 * @name okreniMeApp.cards
 * @description
 * # cards
 * Service in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .service('Cards', function($http, $q, $rootScope, appSettings, $routeParams) {

    function getBySubject(subjectId) {
      var cardsPath = appSettings.baseUrl + '/subject/' + subjectId + '/' + $rootScope.user.id;
      return $http.get(cardsPath)
        .then(function(resp) {
          return resp.data
        });
    }

    function addCard(subjectId, card) {
      var addCardUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/' + subjectId + '/card';
      $http.post(addCardUrl, card)
        .then(function(resp) {
          cards.push(resp.data);
        }, function() {
          // error
        });
    }

    function reportCard(card) {
      var reportUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/report/' + card.id;
      return $http.post(reportUrl);
    }

    function rateCard(card, rating) {
      var rateUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/rate/' + card.id + '/' + rating;
      return $http.post(rateUrl);
    }

    function watchCard(card) {
      var watchUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/watch/' + card.id;
      return $http.post(watchUrl);
    }

    function unwatchCard(card) {
      var watchUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/watch/' + card.id;
      return $http.delete(watchUrl);
    }

    function getWatchCards() {
      var watchUrl = appSettings.baseUrl + '/user/' + $rootScope.user.id + '/watch';
      return $http.get(watchUrl);
    }

    return {
      addCard: addCard,
      getBySubject: getBySubject,
      reportCard: reportCard,
      rateCard: rateCard,
      watchCard: watchCard,
      unwatchCard: unwatchCard,
      getWatchCards: getWatchCards
    };


  });
