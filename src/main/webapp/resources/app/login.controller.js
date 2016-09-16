'use strict';

angular.module('greenApp').controller('loginController', function($scope, $http, $location) {
	
	$scope.login = function() {
		console.log("In login function");
		$http({
			method: 'GET',
			url: _contextPath + "/login" + "?email=" + $scope.email + "&password=" + $scope.password
		})
		.then(function(response){
			console.log("Success in login function");
			let user = response.data;
			$location.path("/profile/" + user.id);
			console.log(user);
		}, function(error){
			console.log("Error in login function");
			console.log(error.data);
		});
	}
	$scope.logout = function() {
		console.log("In logout function");
		$http({
			method: 'GET',
			url: _contextPath + "/logout"
		})
		.then(function(response){
			console.log("Success in logout function");
		}, function(error){
			console.log("Error in logout function");
			console.log(error.data);
		});
	}
});