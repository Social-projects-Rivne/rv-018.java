'use strict';

angular.module('greenApp').controller('loginController', function($scope, $rootScope, $http, $location, $localStorage) {
	
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
});
