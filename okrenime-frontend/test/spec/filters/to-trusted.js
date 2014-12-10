'use strict';

describe('Filter: toTrusted', function () {

  // load the filter's module
  beforeEach(module('okreniMeApp'));

  // initialize a new instance of the filter before each test
  var toTrusted;
  beforeEach(inject(function ($filter) {
    toTrusted = $filter('toTrusted');
  }));

  it('should return the input prefixed with "toTrusted filter:"', function () {
    var text = 'angularjs';
    expect(toTrusted(text)).toBe('toTrusted filter: ' + text);
  });

});
