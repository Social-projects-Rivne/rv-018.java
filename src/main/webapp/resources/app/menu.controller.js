'use strict';

angular.module('greenApp').controller('menuController', function($scope) {

	$scope.checkTabs = false;
	
	$scope.name = "Oracle";
	
	$scope.showTabs = function() {
		$scope.checkTabs = true;
	};
	$scope.hideTabs = function() {
		$scope.checkTabs = false;
	};

});