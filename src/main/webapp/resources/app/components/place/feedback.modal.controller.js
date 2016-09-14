'use strict';

angular
		.module('greenApp')
		.controller(
				'PlaceFeedbackController',
				["$scope", "$http", function($scope, $http) {
					$scope.username = "John Smith";
					$scope.avatar = "/resources/images/user_icon.png";
				}]);


