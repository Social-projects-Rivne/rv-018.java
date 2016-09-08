'use strict';

angular.module('greenApp')
  .component('map', {
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope, $http, CalendarIsOpen, $templateCache) {
    	if ($rootScope.myMap) {
    		$scope.previousMapCenter = $rootScope.myMap.getCenter();
    		$scope.previousMapZoom = $rootScope.myMap.getZoom();
    		$rootScope.myMap.remove(); 		
    	}
    	
    	$scope.removeCache = function() {
    		$templateCache.remove(_contextPath + '/resources/app/components/map/map.template.html');
    	}
    	
    	if ($scope.previousMapCenter) {
    		$rootScope.myMap = L.map('mapid').setView($scope.previousMapCenter, $scope.previousMapZoom);
    	} else {
    		$rootScope.myMap = L.map('mapid').setView([ 50.619900, 26.251617 ], 13);
    	}
    	
		$scope.singletonCalendarIsOpen = CalendarIsOpen;
    	
    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',{
			maxZoom : 18,
			attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
			+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
			+ 'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id : 'mapbox.streets'}).addTo($rootScope.myMap);
		
		var marker;
		$rootScope.myMap.on('click',function(e) {
			marker = new L.Marker(e.latlng, {draggable:true});
			$rootScope.myMap.addLayer(marker);
			$scope.latitude = marker.getLatLng().lat;
			$scope.longitude = marker.getLatLng().lng;
			document.getElementById('latitude').value = $scope.latitude;
			document.getElementById('longitude').value = $scope.longitude;
			marker.setLatLng(e.latlng);
			}
		);
		
		$scope.showOrHideCalendar = function() {
			if ($scope.singletonCalendarIsOpen.getCalendarIsOpen())
				$scope.singletonCalendarIsOpen.setCalendarIsOpen(false);
			else 
				$scope.singletonCalendarIsOpen.setCalendarIsOpen(true);
		};
		
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
					latitude : $scope.latitude,
					longitude : $scope.longitude
				}
			};
			
			var successCallback = function(response){
				$scope.submissionSuccess = true;
				setTimeout(function() {
					$scope.$apply(function() {
						$scope.submissionSuccess = false;
					});
				}, 5000);
		    };
		    
		    var errorCallback = function(response){
				$scope.submissionError = true;
				$scope.submissionSuccess = false;
				setTimeout(function() {
					$scope.$apply(function() {
						$scope.submissionError = false;
					});
				}, 5000);
		    };

			$http.post(_contextPath + "/api/place/", dataObj).then(successCallback, errorCallback);
		};
		
		$scope.resetAddPlaceForm = function(form) {
			$rootScope.myMap.removeLayer(marker);
		};
		
		$rootScope.$emit('initMarkerController', {});
    }
});
