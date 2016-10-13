'use strict';

angular.module('greenApp').controller('menuController', function($rootScope, $scope, $timeout, $mdSidenav, $localStorage) {

	$scope.loginCondition = $localStorage.message;
	$scope.loginstatus = $scope.loginCondition;
	
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
	
	 $scope.showUserProfile = function(){
		 if ($scope.loginstatus == 'logout') {
			 console.log("clickLoginForm")
			 $http({
				 	method: 'GET',
					url: _contextPath + "/user/",
					headers: { 'Authorization': $localStorage.authorization },
				}).then(function successCallback(response) {
				    // this callback will be called asynchronously
				    // when the response is available
				  }, function errorCallback(response) {
				    // called asynchronously if an error occurs
				    // or server returns response with an error status.
				  });	
			 
		} 
		
	 }
});