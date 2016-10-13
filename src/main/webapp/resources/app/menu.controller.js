'use strict';

angular.module('greenApp').controller('menuController', function($rootScope, $scope, $timeout, $mdSidenav, $localStorage, $http) {
	
	$scope.loginCondition = $localStorage.message;
	$scope.loginstatus = $scope.loginCondition;
	var usertoken = $localStorage.authorization;
	if ($scope.loginstatus == "logout") {
		$scope.userprofile = usertoken.substring(6);
	}
	
	 if ($scope.loginstatus == "logout") {
		 	$scope.loginCondition = $localStorage.message;
			
			var token = $localStorage.authorization;
			var token1 = token.substring(6);
		 $http({
			 	method: 'GET',
				url: _contextPath + "/user/profile/" + token1,
				headers: { 'Authorization': $localStorage.authorization },
			}).then(function successCallback(response) {
			    // this callback will be called asynchronously
			    // when the response is available
				console.log("clickLoginForm12")
				$scope.user = response.data;
				$scope.id = response.data.id;
				 console.log($scope.user)
				 console.log("user id = " + $scope.id)
			  }, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });	
		 
		 $scope.showUserProfile = function(){
			 console.log("user id 2 = " + $scope.id)
			
		 }
		 
	}
	 console.log("user id 3 = " + $scope.id)
	
	$scope.checkTabs = false;

	$scope.name = "Oracle";
	
	$scope.showTabs = function() {
		$scope.checkTabs = true;
	};

	$scope.hideTabs = function() {
 ;		$scope.checkTabs = false;
	};

	$rootScope.mopen = function() {
		$scope.checkTabs = true;
	};

	$scope.toggleLeft = buildToggler('left');

	function buildToggler(componentId) {
		return function() {
			$mdSidenav(componentId).toggle();
		}
	}

	$scope.clearSearchLine = function() {
		$scope.searchLine = "";
	};
	
/*	 $scope.showUserProfile = function(){
		 if ($scope.loginstatus == 'logout') {
			 console.log("clickLoginForm")
			 $http({
				 	method: 'GET',
					url: _contextPath + "/user/profile/" + token1,
					headers: { 'Authorization': $localStorage.authorization },
				}).then(function successCallback(response) {
				    // this callback will be called asynchronously
				    // when the response is available
					 console.log("clickLoginForm12")
					$scope.user = response.data;
					$scope.id = response.data.id;
					 console.log($scope.user)
					 console.log($scope.id)
				  }, function errorCallback(response) {
				    // called asynchronously if an error occurs
				    // or server returns response with an error status.
				  });	
			 
		} 
		
	 }*/
});