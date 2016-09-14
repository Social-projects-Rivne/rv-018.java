'use strict';

angular.module('greenApp').controller('EditPlaceController',
		[ "$scope", "$http", function($scope, $http) {
			$scope.name = "";
			$scope.desciption = "";
		} ]);