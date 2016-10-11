'use strict';

describe('fullstack.version module', function() {
  beforeEach(module('fullstack.version'));

  describe('version service', function() {
    it('should return current version', inject(function(version) {
      expect(version).toEqual('0.1');
    }));
  });
});
