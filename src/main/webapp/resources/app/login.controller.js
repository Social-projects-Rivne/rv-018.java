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
	          $scope.forgotPaswordEmail = "";
	    };

	    let errorCallback = function(response){
	      $scope.forgotPaswordEmail = "";
	    };

	    $http.post(_contextPath + "/user/resetPassword", email).then(successCallback, errorCallback);
	 };
	 
	// -----User Registration Functionality-----
	 $scope.userRegistration = function() {
	      let dataObj = {
		      firstName: $scope.firsName,
		      lastName: $scope.lastName,
		      username : $scope.userName,
		      email: $scope.userEmail,
		      password: $scope.userPassword
	      }
	      let email = $scope.userEmail;

	    console.log(dataObj);

	    let successCallback = function(response){
	      Materialize.toast('Success! Check your emeil for confirmation!', 2000);
	      $scope.firsName = "";
	      $scope.lastName = "";
	      $scope.userName = "";
	      $scope.userEmail = "";
	      $scope.userPassword = "";
	    };

	    let errorCallback = function(response){
	    	Materialize.toast('Something wrong. Please try again!', 2000);
	    };

	    $http.post(_contextPath + "/user", dataObj).then(successCallback, errorCallback);
	 };
	 
	 $scope.hideLoginForm = function(){
	      $scope.loginFormIsOpen = false;
	 }
	 
	 $scope.createAccount = function(){
	      $scope.createAccountFormIsOpen = true;
	 }
	 
	 $scope.forgotPassword = function(){
	      $scope.forgotPasswordFormIsOpen = true;
	 }
});