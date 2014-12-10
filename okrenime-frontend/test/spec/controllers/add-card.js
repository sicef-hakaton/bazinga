'use strict';

describe('Controller: AddCardCtrl', function () {

  // load the controller's module
  beforeEach(module('okreniMeApp'));

  var AddCardCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AddCardCtrl = $controller('AddCardCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
