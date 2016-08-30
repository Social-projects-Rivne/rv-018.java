'use strict';

angular.
    module('greenApp').
    component('searchplace', {
        templateUrl: _contextPath + '/resources/app/components/searchPlace/searchplace.template.html',
        controller: ['$scope', '$http', '$routeParams', '$rootScope', function($scope, $http, $routeParams, $rootScope) {
            $scope.searchPlacesSidebarOpen = false;
    	
            $http.get(_contextPath + '/api/place/filter/name?name=' + $routeParams.name + '&ignorecase=true').then(function(response) {
            	$scope.places = response.data;
            	
            	if (response.data.length != 0)
            	    $scope.searchPlacesSidebarOpen = true;  
            });
            
            $scope.closePlacesSidebar = function() {
                $scope.searchPlacesSidebarOpen = false;
            }
            
            $scope.refocusMap = function(latitude, longitude) {
                $rootScope.myMap.panTo(new L.LatLng(latitude, longitude));
            }
        }]
    });