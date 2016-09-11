'use strict';

angular.module('greenApp').controller('loginController', function($scope, $http, $location) {
	
	$scope.login = function() {
		$http({
			method: 'GET',
			url: _contextPath + "/login" + "?email=" + $scope.email + "&password=" + $scope.password
		})
		.then(function(response){
			let user = response.data;
			$location.path("/profile/" + user.id);
			console.log(user);
		}, function(error){
			
			console.log(error.data);
		});
	}
});