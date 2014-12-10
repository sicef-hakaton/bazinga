'use strict';

describe('Controller: UserCardsCtrl', function () {

  // load the controller's module
  beforeEach(module('okreniMeApp'));

  var UserCardsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserCardsCtrl = $controller('UserCardsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
