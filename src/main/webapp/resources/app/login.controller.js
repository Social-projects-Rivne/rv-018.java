'use strict';

angular.module('greenApp').controller('loginController', function($scope, $http, $location) {
	
	$scope.loginCondition = "login";
	
	/* Login and logout functionality */
	$scope.login = function() {
		console.log("In login function");
		$http({
			method: 'GET',
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
			url: _contextPath + "/logout"
		})
		.then(function(response){
			console.log("Success in logout function");
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
	      setTimeout(function () {
	        $scope.$apply(function () {
	          $scope.forgotPaswordEmail = "";
	        });
	      }, 50);
	    };

	    let errorCallback = function(response){
	      Materialize.toast('Something wrong. Please try again!', 2000);
	      $scope.forgotPaswordEmail = "";
	    };

	    $http.post(_contextPath + "/user/resetPassword", email).then(successCallback, errorCallback);
	 };
});