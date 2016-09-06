'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log, CalendarButtonIsShown, CalendarDateRangeIsChosen, MapMarkersArray){
		MapMarkersArray.mapMarkersArrayParam = [];
    	var currentlySelectedTabInnerHtml = "Places";
	    $scope.singletonCalendarButtonIsShown = CalendarButtonIsShown;
		
		$rootScope.$on('initMarkerController', function() {
			$rootScope.myMap.on('moveend', function() {
				var latLngBounds = $rootScope.myMap.getBounds();
				var urlPath = currentlySelectedTabInnerHtml.toLowerCase().substring(0, currentlySelectedTabInnerHtml.length - 1);
				
				if(!CalendarDateRangeIsChosen.getCalendarDateRangeIsChosen()) {
					$scope.progressBarVision = true;
					$http({
						method: 'GET',
						url: _contextPath + '/api/' 
											+ urlPath  
											+ '/point' 
											+ '?south-west=' + latLngBounds.getSouthWest().lat + ':' + latLngBounds.getSouthWest().lng 
											+ '&north-east=' + latLngBounds.getNorthEast().lat + ':' + latLngBounds.getNorthEast().lng
					})
					.then(function(response){
						$scope.progressBarVision = false;
						var points = response.data;
	
						if (MapMarkersArray.mapMarkersArrayParam.length > 0)
							angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
								$rootScope.myMap.removeLayer(marker);
							})
						MapMarkersArray.mapMarkersArrayParam = [];
						
						var markerIcon = null;
						if (urlPath == "place") {
							var BlueIcon = L.Icon.Default.extend ({
								options: {
									iconUrl: 'http://www.clker.com/cliparts/q/I/Q/u/Z/1/marker-hi.png'
								}
							});
							markerIcon = new BlueIcon(); 
						} else if (urlPath = "event") {
							var GreenIcon = L.Icon.Default.extend ({
								options: {
									iconUrl: 'http://www.clker.com/cliparts/G/e/o/0/f/m/map-pin-red-hi.png'
								}
							});
							markerIcon = new GreenIcon(); 					
						}
						
						angular.forEach(points, function(point, key){
							MapMarkersArray.mapMarkersArrayParam.push(L.marker([point.latitude, point.longitude], {icon: markerIcon}).addTo($rootScope.myMap));
						})
					}, function(error){
						$scope.progressBarVision = false;
						$rootScope.error = error;
						$log.info(error);
					});
	
					angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
						$rootScope.myMap.addLayer(marker);
					})
				}
			});
		})
		
		// Check currently selected tab
		$('ul.tabs').on('click', 'a', function(e) {
			CalendarDateRangeIsChosen.setCalendarDateRangeIsChosen(false);
			
			currentlySelectedTabInnerHtml = e.currentTarget.innerHTML;
			
			// Check if calendar button need to be shown
		    if (currentlySelectedTabInnerHtml == "Events")
		    	$scope.singletonCalendarButtonIsShown.setCalendarButtonIsShown(true);
		    else
		    	$scope.singletonCalendarButtonIsShown.setCalendarButtonIsShown(false);
		    	
			if (MapMarkersArray.mapMarkersArrayParam.length > 0)
				angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
					$rootScope.myMap.removeLayer(marker);
				})
				
			MapMarkersArray.mapMarkersArrayParam = [];

			var latLngBounds = $rootScope.myMap.getBounds();
			var urlPath = currentlySelectedTabInnerHtml.toLowerCase().substring(0, currentlySelectedTabInnerHtml.length - 1);
				
			$scope.progressBarVision = true;
			$http({
				method: 'GET',
				url: _contextPath + '/api/' 
					+ urlPath  
					+ '/point' 
					+ '?south-west=' + latLngBounds.getSouthWest().lat + ':' + latLngBounds.getSouthWest().lng 
					+ '&north-east=' + latLngBounds.getNorthEast().lat + ':' + latLngBounds.getNorthEast().lng
			})
			.then(function(response){
				$scope.progressBarVision = false;
				var points = response.data;

				if (MapMarkersArray.mapMarkersArrayParam.length > 0)
					angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
						$rootScope.myMap.removeLayer(marker);
					})
					MapMarkersArray.mapMarkersArrayParam = [];
					
				var markerIcon = null;
				if (urlPath == "place") {
					var BlueIcon = L.Icon.Default.extend ({
						options: {
							iconUrl: 'http://www.clker.com/cliparts/q/I/Q/u/Z/1/marker-hi.png'
						}
					});
					markerIcon = new BlueIcon(); 
				} else if (urlPath = "event") {
					var GreenIcon = L.Icon.Default.extend ({
						options: {
							iconUrl: 'http://www.clker.com/cliparts/G/e/o/0/f/m/map-pin-red-hi.png'
						}
					});
					markerIcon = new GreenIcon(); 					
				}
					
				angular.forEach(points, function(point, key){
					MapMarkersArray.mapMarkersArrayParam.push(L.marker([point.latitude, point.longitude], {icon: markerIcon}).addTo($rootScope.myMap));
				})
			}, function(error){
				$scope.progressBarVision = false;
				$rootScope.error = error;
				$log.info(error);
			});

			angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
				$rootScope.myMap.addLayer(marker);
			})
		});
});