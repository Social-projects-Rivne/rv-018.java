'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log){
		$scope.showMarkers = function() {
			
			var markersArray = [];
//			var mapViewport = $rootScope.myMap.getBounds();
//			var mapViewportGSON = JSON.stringify($rootScope.myMap.getBounds()).replace(/_/g, '');
			
			
//			$http({
//				method: 'GET',
//				url: 'http://localhost:8080/GreenTourism/api/point/'
//			})
//			.then(function(response){
//				$scope.points = response.data;
//				
//				angular.forEach($scope.points, function(point, key){
//					markersArray.push(L.marker([point.lat, point.lon]).addTo($rootScope.myMap));
//				})
//			}, function(error){
//				$scope.error = error;
//				$log.info(error);
//			});

//			$http({
//				method: 'POST',
//				url: 'http://localhost:8080/GreenTourism/api/currentMapViewportPoints/',
//				data: mapViewport
//			})
//			.then(function(response){
//				$scope.points = response.data;
//				
//				angular.forEach($scope.points, function(point, key){
//					markersArray.push(L.marker([point.lat, point.lon]).addTo($rootScope.myMap));
//				})
//			}, function(error){
//				$scope.error = error;
//				$log.info(error);
//			});

			$rootScope.myMap.on('move', function(){
				angular.forEach(markersArray, function(marker, key){
					$rootScope.myMap.removeLayer(marker);
				})

				markersArray = [];
				var mapViewportGSON = JSON.stringify($rootScope.myMap.getBounds()).replace(/_/g, '');

				$http({
					method: 'POST',
					url: 'http://localhost:8080/GreenTourism/api/currentMapViewportPoints/',
					data: mapViewportGSON
				})
				.then(function(response){
					$scope.points = response.data;

					if (markersArray.length > 0)
						angular.forEach(markersArray, function(marker, key){
							$rootScope.myMap.removeLayer(marker);
						})
					markersArray = [];
					
					angular.forEach($scope.points, function(point, key){
						markersArray.push(L.marker([point.lat, point.lon]).addTo($rootScope.myMap));
					})
				}, function(error){
					$scope.error = error;
					$log.info(error);
				});

				angular.forEach(markersArray, function(marker, key){
					$rootScope.myMap.addLayer(marker);
				})
			});
		}
});