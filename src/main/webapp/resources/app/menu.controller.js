'use strict';

angular.module('greenApp').controller('menuController', function($scope, $timeout, $mdSidenav) {

	$scope.checkTabs = false; 

	$scope.name = "Oracle";
	
	$scope.showTabs = function() {
		$scope.checkTabs = true;
	};

	$scope.hideTabs = function() {
		$scope.checkTabs = false;
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