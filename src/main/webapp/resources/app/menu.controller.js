'use strict';

angular.module('greenApp').controller('menuController', function($rootScope, $scope, $timeout, $mdSidenav) {

	$scope.checkTabs = false ;

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
});