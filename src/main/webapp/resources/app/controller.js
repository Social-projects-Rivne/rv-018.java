$('.sideNavMenuButton').sideNav({
    menuWidth: 300, // Default is 240
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  }
);

/*$('ul.tabs').on('click', 'a', function(e) {
	var r = 3;
    //Your code
});*/

angular.module('greenApp')
	.controller('mainController', function($scope){
		
		var headerClientWidth = document.getElementById('header').clientHeight;
		var tabsRowClientWidth = document.getElementById('tabsRow').clientHeight;
		
		$scope.topMarginValue = headerClientWidth + tabsRowClientWidth;
	});
