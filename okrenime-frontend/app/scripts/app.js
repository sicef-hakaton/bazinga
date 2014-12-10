'use strict';

/**
 * @ngdoc overview
 * @name okreniMeApp
 * @description
 * # okreniMeApp
 *
 * Main module of the application.
 */

var logsOutUserOn401 = function($location, $q, SessionService, FlashService) {
  var success = function(response) {
    return response;
  };

  var error = function(response) {
    if (response.status === 401) {
      SessionService.unset('authenticated');
      $location.path('/login');
      FlashService.show(response.data.flash);
    }
    return $q.reject(response);
  };

  return function(promise) {
    return promise.then(success, error);
  };
};

angular
  .module('okreniMeApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.select'
  ])

.config(function($routeProvider, $httpProvider) {
    $routeProvider
      .when('/home', {
        templateUrl: 'views/user-cards.html',
        controller: 'UserCardsCtrl'
      })
      .when('/card/add', {
        templateUrl: 'views/add-card.html',
        controller: 'AddCardCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login-register.html',
        controller: 'LoginCtrl'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl'
      })
      .otherwise({
        redirectTo: '/login'
      });

    $httpProvider.interceptors.push(logsOutUserOn401);
    // $httpProvider.defaults.useXDomain = true;
    // delete $httpProvider.defaults.headers.common['X-Requested-With'];
    // $httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
  })
  .run(function($rootScope, $location, AuthenticationService, FlashService, appSettings, $http) {
    var routesThatRequireAuth = ['/', '/home', '/card'];

    $rootScope.$on('$routeChangeStart', function(event, next, current) {
      var requiresAuth = _.any(routesThatRequireAuth, function(route) {
        return $location.path().indexOf(route) !== -1;
      });
      if (requiresAuth && !AuthenticationService.isLoggedIn()) {
        $location.path('/login');
        FlashService.show("Please log in to continue.");
      }
    });

    if (sessionStorage['authenticated']) {
      $rootScope.user = JSON.parse(sessionStorage['authenticated']);
    }

    $rootScope.getArray = function(num) {
      return _.range(num);
    }

    $rootScope.appSettings = appSettings;

    $rootScope.logout = function() {
      console.log('asdf');
      sessionStorage.removeItem('authenticated');
      window.location.href = '';
    }
  })

.factory("FlashService", function($rootScope) {
  return {
    show: function(message) {
      $rootScope.flash = message;
    },
    clear: function() {
      $rootScope.flash = "";
    }
  }
})

.factory("SessionService", function() {
  return {
    get: function(key) {
      return sessionStorage.getItem(key);
    },
    set: function(key, val) {
      return sessionStorage.setItem(key, val);
    },
    unset: function(key) {
      return sessionStorage.removeItem(key);
    }
  }
});
