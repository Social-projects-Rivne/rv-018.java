'use strict';

angular.module('greenApp').controller('loginController',['$scope','$rootScope','$http', '$location', function($scope, $rootScope, $http, $location) {
	
	$scope.loginCondition = "login";
	
	/* Login and logout functionality */
	$scope.login = function() {
		console.log("In login function");
		$http({
			method: 'POST',
			url: _contextPath + "/login" + "?email=" + $scope.email + "&password=" + $scope.password
		})
		.then(function(response){
			$rootScope.authorization = response.headers('Authorization'); 
			console.log("Headers sended by java: " + $rootScope.authorization);
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
			method: 'PATCH',
			url: _contextPath + '/logout',
			headers: { 'Authorization': $rootScope.authorization }
		})
		.then(function(response){
			console.log("Success in logout function");
			$scope.loginCondition = "login";
		}, function(error){
			console.log("Error in logout function");
			console.log(error.data);
		});
		
		$scope.loginCondition = "login";
	};
	
	// -----Forgot Password Functionality-----
	 $scope.forgotPassword = function() {
	      let email = $scope.forgotPaswordEmail;

	    console.log(email);

	    let successCallback = function(response){
	      Materialize.toast('Resetting email was sent!', 2000);
	          $scope.forgotPaswordEmail = "";
	    };

	    let errorCallback = function(response){
	      $scope.forgotPaswordEmail = "";
	    };

	    $http.post(_contextPath + "/user/resetPassword", email).then(successCallback, errorCallback);
	 };
}]);
