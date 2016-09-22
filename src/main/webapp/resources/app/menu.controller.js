'use strict';

angular.module('greenApp').controller('menuController', function(TapIsOpen,$scope) {

	$scope.checkTabs = false; 

	TapIsOpen.Open = function() {
		$scope.checkTabs = true;
		
		var sideNav = document.getElementById('collection') ;
		sideNav.style.height=88+"vh";
		sideNav.style.top=68+"px"; 
	}; 
	
	$scope.name = "Oracle";
	
	$scope.showTabs = function() {
		$scope.checkTabs = true;
	};
	$scope.hideTabs = function() {
		$scope.checkTabs = false;
	};

});