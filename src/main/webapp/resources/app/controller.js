$('.sideNavMenuButton').sideNav({
    menuWidth: 300, // Default is 240
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  }
);

angular.module('greenApp')
	.controller('mainController', function($scope){
		
		var buttonClick = true;
		var headerClientWidth = document.getElementById('header').clientHeight;
		var tabsRowClientWidth = document.getElementById('tabsRow').clientHeight;
		
		$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;
				
		$scope.menuButtonClick = function() {
				
			if (buttonClick) {
				buttonClick = false;
				$scope.contentCss = {
						'margin-left': '6.13%',
						'width': '83.4%'
				};
			} else {
				buttonClick = true;
				$scope.contentCss = {
						'margin-left': '0px',
						'width': '100%'
				};
			}
		}
	});