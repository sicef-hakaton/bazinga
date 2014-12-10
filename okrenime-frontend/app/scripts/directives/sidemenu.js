'use strict';

/**
 * @ngdoc directive
 * @name okreniMeApp.directive:sidemenu
 * @description
 * # sidemenu
 */
angular.module('okreniMeApp')
  .directive('sidemenu', function() {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {

      }
    };
  });
