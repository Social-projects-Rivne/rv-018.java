'use strict';

angular.module('greenApp').controller('menuController', function($scope) {
	
	$scope.checkTabs = false;

	$scope.showTabs = function() {
		$scope.checkTabs = true;
		console.log("ShowTabs: $scope.checkTabs = " + $scope.checkTabs);
	};
	$scope.hideTabs = function() {
		$scope.checkTabs = false;
		console.log("HideTabs: $scope.checkTabs = " + $scope.checkTabs);
	};

});