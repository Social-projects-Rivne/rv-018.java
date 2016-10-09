'use strict';

angular.module('greenApp').controller('loginController', function($scope, $rootScope, $http, $location, $localStorage) {
	$scope.loginFormShow = '' ;
	$scope.loginCondition = $localStorage.message;
	console.log('Authorization : ' + $localStorage.authorization);
	console.log($localStorage.message);
	if ($scope.loginCondition == null) {
		$scope.loginCondition = 'login';
	}
	console.log($scope.loginCondition);
	/* Login and logout functionality */
	$scope.login = function() {
		console.log("In login function");
		$http({
			method: 'POST',
			url: _contextPath + "/login" + "?email=" + $scope.email + "&password=" + $scope.password
		})
		.then(function(response){
			$localStorage.authorization = response.headers('Authorization'); 
			$scope.email = '';
			$scope.password = '';
			$localStorage.message = 'logout';
			$scope.loginCondition = $localStorage.message;
			$scope.loginFormShow = 'hideform' ;
		}, function(error){
			console.log("Error in login function");
			console.log(error.data);
			$scope.inputError = true;
		});
	}
	$scope.logout = function() {
		console.log("In logout function");
		$http({
			method: 'PATCH',
			url: _contextPath + '/logout',
			headers: { 'Authorization': $localStorage.authorization }
		})
		.then(function(response){
			console.log('Success in logout function');
			$scope.loginFormShow = '' ;
			$localStorage.message = 'login';
			$scope.loginCondition = $localStorage.message;
			$localStorage.authorization = null;
		}, function(error){
			console.log('Error in logout function');
			console.log(error.data);
		});
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
	 
	 $scope.forgotPasswordForm = function(){
	      $scope.forgotPasswordFormIsOpen = true;
	 }
	 
	 $scope.openLoginForm = function(){
	      $scope.loginFormIsOpen = true;
	 }
	 $rootScope.showLoginForm = function(){
		 if ($scope.loginCondition == null) {
			 $scope.loginCondition = 'login';
			 console.log("clickLoginForm")
			 $scope.loginFormIsOpen = false;
			 $scope.createAccountFormIsOpen = false;
			 $scope.forgotPasswordFormIsOpen = false;
			 $scope.loginFormIsOpen = true;
		}
		 $scope.loginFormIsOpen = false;
		 $scope.createAccountFormIsOpen = false;
		 $scope.forgotPasswordFormIsOpen = false;
		 $scope.loginFormIsOpen = true;
	 }	 
});
