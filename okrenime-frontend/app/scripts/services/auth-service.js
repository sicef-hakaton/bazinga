'use strict';

/**
 * @ngdoc service
 * @name okreniMeApp.authService
 * @description
 * # authService
 * Service in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .factory("AuthenticationService", function($http, $rootScope, $sanitize, $q, SessionService, FlashService, appSettings) {

    var cacheSession = function(data) {
      SessionService.set('authenticated', JSON.stringify(data));
      $rootScope.user = data;
    };

    var uncacheSession = function() {
      SessionService.unset('authenticated');
    };

    var httpError = function(response) {
      if (response) {
        FlashService.show(response.flash);
      }
    };

    var sanitizeCredentials = function(credentials) {
      return {
        email: $sanitize(credentials.email),
        password: $sanitize(credentials.password),
      };
    };

    return {
      login: function(credentials) {
        var login = $http.post(appSettings.baseUrl + "/user/login", sanitizeCredentials(credentials));
        login.success(cacheSession);
        login.success(FlashService.clear);
        login.error(httpError);
        return login;
      },

      register: function(credentials) {
        var register = $http.post(appSettings.baseUrl + "/user/register", sanitizeCredentials(credentials));
        register.success(cacheSession);
        register.success(FlashService.clear);
        register.error(httpError);
        return register;
      },

      logout: function() {
        var logout = $http.get("/auth/logout");
        logout.success(uncacheSession);
        return logout;
      },
      isLoggedIn: function() {
        return SessionService.get('authenticated');
      }
    };
  });
