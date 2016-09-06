'use strict';

angular.
    module('greenApp').
    component('searchplace', {
        templateUrl: _contextPath + '/resources/app/components/searchPlace/searchplace.template.html',
        controller: ['$scope', '$http', '$routeParams', '$rootScope', function($scope, $http, $routeParams, $rootScope) {
    	    $scope.address = [];
    	    $scope.counter = 0;
            $http.get(_contextPath + '/api/place/filter/name?name=' + $routeParams.name + '&ignorecase=true').then(function(response) {
             	$scope.places = response.data;
             	$scope.searchPlacesSidebarOpen = true;
             	 
             	if (response.data.length == 0)
             	   $scope.noSuchResultMessage = true;
             	
             	for (var i = 0; i < $scope.places.length; i++) {
             		$scope.getAddressOfPoint($scope.places[i].point.latitude, $scope.places[i].point.longitude);

             		$scope.places[i].address = $scope.address[i];
             	}
             	
             });
            
            $scope.closePlacesSidebar = function() {
                $scope.searchPlacesSidebarOpen = false;
            }
            
            $scope.refocusMap = function(latitude, longitude) {
                $rootScope.myMap.panTo(new L.LatLng(latitude, longitude));
            }
            
            $scope.getAddressOfPoint = function(latitude, longitude) {
             	$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng=' + latitude + ',' + longitude)
             		.then(function(response) {
             			$scope.address.push(response.data.results[0].address_components[1].long_name 
             				+ ', ' + response.data.results[0].address_components[2].long_name);
             			
             			$scope.places[$scope.counter].address = $scope.address[$scope.counter];
             			$scope.counter++;
             	});
             }
        }]
    });