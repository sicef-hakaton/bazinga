'use strict';

describe('Service: educationalUnit', function () {

  // load the service's module
  beforeEach(module('okreniMeApp'));

  // instantiate service
  var educationalUnit;
  beforeEach(inject(function (_educationalUnit_) {
    educationalUnit = _educationalUnit_;
  }));

  it('should do something', function () {
    expect(!!educationalUnit).toBe(true);
  });

});
