'use strict';

angular.
  module('greenApp').
    component('searchplace', {
      templateUrl: _contextPath + '/resources/app/components/searchPlace/searchplace.template.html',
      controller: ['$scope', '$http', '$routeParams', '$rootScope', function($scope, $http, $routeParams, $rootScope) {
    	$scope.searchPlacesSidebarOpen = true;
    	
    	$http.get(_contextPath + '/api/place/filter/name?name=' + $routeParams.name + '&ignorecase=true').then(function(response) {
  		  $scope.places = response.data;
  		  console.log("request")
    	});
    	
    	$scope.closePlacesSidebar = function() {
    		$scope.searchPlacesSidebarOpen = false;
    	}
    	
    	$scope.refocusMap = function(latitude, longitude) {
    		console.log("inside refocus")
    		$rootScope.myMap.panTo(new L.LatLng(latitude, longitude));
    	}
      }]
    });