/*'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log){
		$rootScope.$on('initMarkerController', function() {
			var markersArray = [];

			$rootScope.myMap.on('moveend', function(){
				var latLngBounds = $rootScope.myMap.getBounds();

				$scope.progressBarVision = true;
				$http({
					method: 'GET',
					url: _contextPath + '/api/place/point' 
										+ '?south-west=' + latLngBounds.getSouthWest().lat + ':' + latLngBounds.getSouthWest().lng 
										+ '&north-east=' + latLngBounds.getNorthEast().lat + ':' + latLngBounds.getNorthEast().lng
				})
				.then(function(response){
					$scope.progressBarVision = false;
					var points = response.data;

					if (markersArray.length > 0)
						angular.forEach(markersArray, function(marker, key){
							$rootScope.myMap.removeLayer(marker);
						})
					markersArray = [];
					
					angular.forEach(points, function(point, key){
						markersArray.push(L.marker([point.lat, point.lng]).addTo($rootScope.myMap));
					})
				}, function(error){
					$scope.progressBarVision = false;
					$rootScope.error = error;
					$log.info(error);
				});

				angular.forEach(markersArray, function(marker, key){
					$rootScope.myMap.addLayer(marker);
				})
			});
		})
});*/