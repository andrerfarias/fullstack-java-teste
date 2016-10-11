'use strict';

angular.module('fullstack.version', [
  'fullstack.version.interpolate-filter',
  'fullstack.version.version-directive'
])

.value('version', '0.1');
