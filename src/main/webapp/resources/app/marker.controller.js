'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log){
		$scope.showMarkers = function() {
			
			var markersArray = [];
			
			$http({
				method: 'GET',
				url: 'http://localhost:8080/GreenTourism/api/point/'
			})
			.then(function(response){
				$scope.points = response.data;
				
				angular.forEach($scope.points, function(point, key){
					markersArray.push(L.marker([point.lat, point.lon]).addTo($rootScope.myMap));
				})
			}, function(error){
				$scope.error = error;
				$log.info(error);
			});
			
			$rootScope.myMap.on('move', function(){
				angular.forEach(markersArray, function(marker, key){
					if(!$rootScope.myMap.getBounds().contains(marker.getLatLng())){
						$rootScope.myMap.removeLayer(marker);
					} else {
						$rootScope.myMap.addLayer(marker);
					}
				})
			});
		}
});