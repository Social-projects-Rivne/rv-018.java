'use strict';

angular.module('greenApp')
  .component('map', {
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope, $http) {
    	var map = L.map('mapid').setView([ 50.619900, 26.251617 ], 13);
			L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',{
			maxZoom : 18,
			attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
			+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
			+ 'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id : 'mapbox.streets'}).addTo(map);
		
		var marker;
		map.on('click',function(e) {
			marker = new L.Marker(e.latlng, {draggable:true});
			map.addLayer(marker);
			$scope.latitude = marker.getLatLng().lat;
			$scope.longitude = marker.getLatLng().lng;
			document.getElementById('latitude').value = $scope.latitude;
			document.getElementById('longitude').value = $scope.longitude;
			marker.setLatLng(e.latlng);
			}
		);
		
		$scope.addPlaceMenu = function() {
			$scope.addPlaceMenuIsOpen = true;
		};

		$scope.toggleAddPlaceMenu = function() {
			$scope.addPlaceMenuIsOpen = false;
		};
		
		$scope.hideButtonAddPlace = function(){
			$scope.addButtonAddPlace = false;
		}
		
		$scope.togleButtonAddPlace = function() {
			$scope.addButtonAddPlace = true;
		};

		$scope.createNewPlace = function(form) {
			var dataObj = {
				name : $scope.newPlaceName,
				category : $scope.newPlaceType,
				description : $scope.newPlaceDescription,
				point : {
					lat : $scope.latitude,
					lng : $scope.longitude
				}
			};

			console.log("addPlace", dataObj);

			$http.post(_contextPath + "/api/addplace/", dataObj)

			.success(function(data, status, headers, config) {
				console.log("New Place Added Successfully");
			});
		};
		
		$scope.resetAddPlaceForm = function(form) {
			map.removeLayer(marker);
			$scope.latitude = null;
			$scope.longitude = null;
			$scope.newPlaceName = null;
			$scope.newPlaceDescription = null;
			$scope.newPlacePhotos = null;
		};
		
		$rootScope.$emit('initMarkerController', {});
    }
  });