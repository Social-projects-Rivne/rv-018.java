'use strict';

angular.
  module('greenApp').
    component('searchplace', {
      templateUrl: _contextPath + '/resources/app/components/searchPlace/searchplace.template.html',
      controller: ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
    	$scope.searchPlacesSidebarOpen = true;
    	
    	$http.get(_contextPath + '/api/place/filter/name?name=' + $routeParams.name + '&ignorecase=true').then(function(response) {
  		  $scope.places = response.data;
    	});
    	
    	$scope.closePlacesSidebar = function() {
    		$scope.searchPlacesSidebarOpen = false;
    	}
      }]
    });