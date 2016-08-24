'use strict';

angular.module('greenApp')
  .component('map', {
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope, $http, $log) {
    	var myMap = L.map('mapid', { zoomControl:false }).setView([51.505, -0.09], 13);
    	$rootScope.myMap = myMap;
    	
    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id: 'mapbox.streets'
		}).addTo(myMap);
    	
    	L.marker([51.5, -0.09]).addTo(myMap)
			.bindPopup("I am a popup").openPopup();

		L.circle([51.508, -0.11], 500, {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5
		}).addTo(myMap).bindPopup("I am a circle.");

		L.polygon([
			[51.509, -0.08],
			[51.503, -0.06],
			[51.51, -0.047]
		]).addTo(myMap).bindPopup("I am a polygon.");

		var popup = L.popup();

		function onMapClick(e) {
			popup
				.setLatLng(e.latlng)
				.setContent("You clicked the map at " + e.latlng.toString())
				.openOn(myMap);
		}

		myMap.on('click', onMapClick);
		
		var markersArray = [];

		myMap.on('moveend', function(){
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
						myMap.removeLayer(marker);
					})
				markersArray = [];
				
				angular.forEach(points, function(point, key){
					markersArray.push(L.marker([point.lat, point.lng]).addTo(myMap));
				})
			}, function(error){
				$scope.progressBarVision = false;
				$log.info(error);
			});

			angular.forEach(markersArray, function(marker, key){
				myMap.addLayer(marker);
			})
		})
    }
  });

