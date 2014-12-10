'use strict';

/**
 * @ngdoc filter
 * @name okreniMeApp.filter:toTrusted
 * @function
 * @description
 * # toTrusted
 * Filter in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .filter('toTrusted', function($sce) {
    return function(input) {
      return $sce.trustAsHtml(input);
    };
  });
