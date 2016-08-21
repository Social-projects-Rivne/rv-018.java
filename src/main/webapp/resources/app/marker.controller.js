/*'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log){
		$rootScope.$on('initMarkerController', function() {
			var markersArray = [];

			$rootScope.myMap.on('moveend', function(){
				var mapViewportGSON = JSON.stringify($rootScope.myMap.getBounds()).replace(/_/g, '');

				$scope.progressBarVision = true;
				$http({
					method: 'POST',
					url: _contextPath + '/api/currentMapViewportPoints/',
					data: mapViewportGSON
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