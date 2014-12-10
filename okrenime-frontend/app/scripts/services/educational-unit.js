'use strict';

/**
 * @ngdoc service
 * @name okreniMeApp.educationalUnit
 * @description
 * # educationalUnit
 * Service in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .service('EducationalUnit', function($http, $q, appSettings) {
    var units = [];

    function getAll() {
      if (!units.length) {
        return $http({
          method: 'GET',
          url: appSettings.baseUrl + '/educationalunit'
        }).then(function(resp) {
          units = resp.data;
          return resp.data;
        });
      } else {
        var d = $q.defer();
        d.resolve(units);
        return d.promise;
      }
    }

    return {
      getAll: getAll
    }
  });
