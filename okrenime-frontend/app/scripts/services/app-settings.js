'use strict';

/**
 * @ngdoc service
 * @name okreniMeApp.appSettings
 * @description
 * # appSettings
 * Service in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .service('appSettings', function() {
    return {
      baseUrl: 'http://192.168.1.104:8090'
        // baseUrl: 'http://localhost:8080/rest'
    }
  });
