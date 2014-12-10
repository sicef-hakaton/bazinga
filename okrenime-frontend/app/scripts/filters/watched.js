'use strict';

/**
 * @ngdoc filter
 * @name okreniMeApp.filter:watched
 * @function
 * @description
 * # watched
 * Filter in the okreniMeApp.
 */
angular.module('okreniMeApp')
  .filter('watched', function() {
    return function(items, shouldWatch) {
      var temp = _.filter(items, function(item) {
        if (shouldWatch) {
          return item.watch == shouldWatch;
        } else {
          return true;
        }
      });
      return temp;
    };
  });
