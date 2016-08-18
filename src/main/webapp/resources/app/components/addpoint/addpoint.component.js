'use strict';

angular.
  module('greenApp').
  component('addpoint', {
	  templateUrl: 'resources/app/components/addpoint/addpoint.template.html',
	  controller: function($scope) {
		  		  
		 $scope.addPlaceMenu =function(){
		      $scope.addPlaceMenuIsOpen = true;

		  };
		  
		  $scope.toggleAddPlaceMenu =function(placeMaker){
		      $scope.addPlaceMenuIsOpen = false;

		  };
		 
		}
  });
