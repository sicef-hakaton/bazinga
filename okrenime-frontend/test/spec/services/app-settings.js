'use strict';

describe('Service: appSettings', function () {

  // load the service's module
  beforeEach(module('okreniMeApp'));

  // instantiate service
  var appSettings;
  beforeEach(inject(function (_appSettings_) {
    appSettings = _appSettings_;
  }));

  it('should do something', function () {
    expect(!!appSettings).toBe(true);
  });

});
