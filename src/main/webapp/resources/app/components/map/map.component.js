'use strict';

angular.module('greenApp')
  .component('map', {
//<<<<<<< HEAD
//    templateUrl: 'resources/app/components/map/map.template.html',
//    controller: function($rootScope, $scope) {
//=======
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope) {
//>>>>>>> 66bb7fbb0b5a95fb306a3cbf66520e134e886147
    	var mymap = L.map('mapid').setView([51.505, -0.09], 13);
    	$rootScope.myMap = mymap;
    	
    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
			maxZoom: 18,
			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id: 'mapbox.streets'
		}).addTo(mymap);
    	
    	L.marker([51.5, -0.09]).addTo(mymap)
			.bindPopup("I am a popup").openPopup();

		L.circle([51.508, -0.11], 500, {
			color: 'red',
			fillColor: '#f03',
			fillOpacity: 0.5
		}).addTo(mymap).bindPopup("I am a circle.");

		L.polygon([
			[51.509, -0.08],
			[51.503, -0.06],
			[51.51, -0.047]
		]).addTo(mymap).bindPopup("I am a polygon.");

		var popup = L.popup();

		function onMapClick(e) {
			popup
				.setLatLng(e.latlng)
				.setContent("You clicked the map at " + e.latlng.toString())
				.openOn(mymap);
		}

		mymap.on('click', onMapClick);
		
		$rootScope.$emit('initMarkerController', {});
    }
  });
