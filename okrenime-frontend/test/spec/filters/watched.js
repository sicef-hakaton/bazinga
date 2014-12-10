'use strict';

describe('Filter: watched', function () {

  // load the filter's module
  beforeEach(module('okreniMeApp'));

  // initialize a new instance of the filter before each test
  var watched;
  beforeEach(inject(function ($filter) {
    watched = $filter('watched');
  }));

  it('should return the input prefixed with "watched filter:"', function () {
    var text = 'angularjs';
    expect(watched(text)).toBe('watched filter: ' + text);
  });

});
