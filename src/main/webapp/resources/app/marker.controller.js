'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log, 
			$location, CalendarButtonIsShown, CalendarDateRangeIsChosen, MapMarkersArray, CurrentlySelectedTab){
		MapMarkersArray.mapMarkersArrayParam = [];
		console.log($location.path());
		var match = $location.path().match(/^\/map\/(.+?)\//);
		if (match){
			var currentlySelectedTabInnerHtml = match[1]+'s';
		} else {
			var currentlySelectedTabInnerHtml = 'places';
		}
    	
    	$rootScope.tab=currentlySelectedTabInnerHtml;
    	
    	CurrentlySelectedTab.setCurrentlySelectedTab(currentlySelectedTabInnerHtml);
	    $scope.singletonCalendarButtonIsShown = CalendarButtonIsShown;
		
		$rootScope.$on('initMarkerController', function() {
			$rootScope.myMap.on('moveend', function() {
				var latLngBounds = $rootScope.myMap.getBounds();
				var urlPath = currentlySelectedTabInnerHtml.toLowerCase().substring(0, currentlySelectedTabInnerHtml.length - 1);
				
				console.log(currentlySelectedTabInnerHtml, urlPath);
				if(!CalendarDateRangeIsChosen.getCalendarDateRangeIsChosen()) {
					$scope.progressBarVision = true;
					$http({
						method: 'GET',
						url: _contextPath + '/api/' 
											+ urlPath  
											+ '/' + urlPath + 's_coordinates' 
											+ '?south-west=' + latLngBounds.getSouthWest().lat + ':' + latLngBounds.getSouthWest().lng 
											+ '&north-east=' + latLngBounds.getNorthEast().lat + ':' + latLngBounds.getNorthEast().lng
					})
					.then(function(response){
						$scope.progressBarVision = false;
						let places = response.data;
	
						if (MapMarkersArray.mapMarkersArrayParam.length > 0)
							angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
								$rootScope.myMap.removeLayer(marker);
							})
						MapMarkersArray.mapMarkersArrayParam = [];
						
						var markerIcon = null;
						if (urlPath == "place") {
							let BlueIcon = L.Icon.Default.extend ({
								options: {
									iconUrl: 'http://www.clker.com/cliparts/q/I/Q/u/Z/1/marker-hi.png'
								}
							});
							markerIcon = new BlueIcon(); 
						} else if (urlPath = "event") {
							let GreenIcon = L.Icon.Default.extend ({
								options: {
									iconUrl: 'http://www.clker.com/cliparts/G/e/o/0/f/m/map-pin-red-hi.png'
								}
							});
							markerIcon = new GreenIcon(); 					
						}
						
						angular.forEach(places, function(place, key){
							console.log(place);
							MapMarkersArray.mapMarkersArrayParam
							.push(L.marker([place.point.latitude, place.point.longitude], {icon: markerIcon})
									.addTo($rootScope.myMap)
									.on('click',function (e) {
								$log.info(e + "was clicked")
								window.location = '/#/' + urlPath + '-details/' + place.id;
								}));
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
			CurrentlySelectedTab.setCurrentlySelectedTab(currentlySelectedTabInnerHtml);
			
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
				let points = response.data;

				if (MapMarkersArray.mapMarkersArrayParam.length > 0)
					angular.forEach(MapMarkersArray.mapMarkersArrayParam, function(marker, key){
						$rootScope.myMap.removeLayer(marker);
					})
					MapMarkersArray.mapMarkersArrayParam = [];
					
				var markerIcon = null;
				if (urlPath == "place") {
					let BlueIcon = L.Icon.Default.extend ({
						options: {
							iconUrl: 'http://www.clker.com/cliparts/q/I/Q/u/Z/1/marker-hi.png'
						}
					});
					markerIcon = new BlueIcon(); 
				} else if (urlPath = "event") {
					let GreenIcon = L.Icon.Default.extend ({
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