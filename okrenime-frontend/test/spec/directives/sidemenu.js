'use strict';

describe('Directive: sidemenu', function () {

  // load the directive's module
  beforeEach(module('okreniMeApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<sidemenu></sidemenu>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the sidemenu directive');
  }));
});
