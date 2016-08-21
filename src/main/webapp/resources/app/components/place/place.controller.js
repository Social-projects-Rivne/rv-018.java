angular.module('greenApp')
	.controller('sideNavMenuController', function($scope){
/*		  $scope.$on('$viewContentLoaded', function(){
				var headerClientWidth = angular.element(document.getElementsByClassName('header')).clientWidth;
				var tabsRowClientWidth = document.getElementsByClassName('tabsRow').clientWidth;
				$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;		
		  });  */ 
		  
//		  angular.element(document).ready(function () {
				var headerClientWidth = document.getElementById('header').clientHeight;
				var tabsRowClientWidth = document.getElementById('tabsRow').clientHeight;
				$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;		
//			});
	});