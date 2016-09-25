'use strict';

angular.module('greenApp').controller('loginController',['$scope', '$http', '$location', 'base64', function($scope, $http, $location, base64) {
	
	$scope.loginCondition = "login";
	
	/* Login and logout functionality */
	$scope.login = function() {
		console.log("In login function");
		// encode credentials and create header
		var encodedUserData = base64.encode($scope.email + ":" + $scope.password); 
		var authorizationHeader = 'Basic ' + encodedUserData;
		$http({
			method: 'POST',
			url: _contextPath + "/login" + "?email=" + $scope.email + "&password=" + $scope.password
		})
		.then(function(response){
			console.log("Headers sended by java: " + response.headers('UserId'));
			$scope.email = "";
			$scope.password = "";
			$scope.loginCondition = "logout";
		}, function(error){
			console.log("Error in login function");
			console.log(error.data);
		});
	}
	$scope.logout = function() {
		console.log("In logout function");
		$http({
			method: 'GET',
			url: _contextPath + '/logout',
			headers: { 'Authorization': 'Gosdfsdfs:sdfsddfhell' }
		})
		.then(function(response){
			console.log("Success in logout function");
		}, function(error){
			console.log("Error in logout function");
			console.log(error.data);
		});
		$scope.loginCondition = "login";
	}
}]);