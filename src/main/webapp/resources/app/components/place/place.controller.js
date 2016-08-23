angular.module('greenApp')
	.controller('mainController', function($scope, mapTopValue){
		
		var buttonClick = true;
/*		  $scope.$on('$viewContentLoaded', function(){
				var headerClientWidth = angular.element(document.getElementsByClassName('header')).clientWidth;
				var tabsRowClientWidth = document.getElementsByClassName('tabsRow').clientWidth;
				$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;		
		  });  */ 
		  
//		  angular.element(document).ready(function () {
				var headerClientWidth = document.getElementById('header').clientHeight;
				var tabsRowClientWidth = document.getElementById('tabsRow').clientHeight;
				$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;
	//			topMarginSharedValue.setTopMarginValue($scope.topMarginValue);
//			});
				
				$scope.menuButtonClick = function() {
				
					if (buttonClick){
						buttonClick = false;
						$scope.contentCss = {
								'margin-left': '6.13%',
								'width': '83.4%'
						};
/*						$scope.mapMarginLeftValue = '4.34%';
						$scope.mapWidth = '85%';
*/					} else {
						buttonClick = true;
						$scope.contentCss = {
								'margin-left': '0px',
								'width': '100%'
						};
/*						$scope.mapMarginLeftValue = '0px';
						$scope.mapWidth = '100%';						
*/					}
				}
	});