'use strict';

angular.
  module('greenApp').
  component('addbutton', {
	  templateUrl: 'resources/app/components/addbutton/addbutton.template.html',
	  controller: function($scope, $window) {
		  $scope.AddPlaceMenu = function() {
		    $window.open('resources/app/components/addpoint/addpoint.template.html');
		  };
		}
  });
  