'use strict';

angular.module('greenApp').controller('menuController', function($scope) {

	$scope.checkMenu = true;
	$scope.checkTabs = false;
	$scope.checkContent = false;

	$scope.showMenu = function() {
		$scope.checkMenu = !$scope.checkMenu;
		console.log("ShowMenu: $scope.checkMenu = " + $scope.checkMenu);
	};
	$scope.moveContent = function() {
		$scope.checkContent = !$scope.checkContent;
		console.log("MoveContent: $scope.checkContent = " + $scope.checkContent);
	};
	$scope.showTabs = function() {
		$scope.checkTabs = true;
		console.log("ShowTabs: $scope.checkTabs = " + $scope.checkTabs);
	};
	$scope.hideTabs = function() {
		$scope.checkTabs = false;
		console.log("HideTabs: $scope.checkTabs = " + $scope.checkTabs);
	};

});