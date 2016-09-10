'use strict';

angular.module('greenApp')
  .component('map', {
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope, $http, $routeParams, CalendarIsOpen, CalendarButtonIsShown, $templateCache) {
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
		$scope.singletonCalendarButtonIsShown = CalendarButtonIsShown;

    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',{
			maxZoom : 18,
			attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
			+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
			+ 'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id : 'mapbox.streets'}).addTo($rootScope.myMap);

      var marker;
      $rootScope.myMap.on('click',function(e) {
        if (typeof(marker) === 'undefined') {
          marker = new L.Marker(e.latlng);
          $rootScope.myMap.addLayer(marker);
          $scope.latitude = marker.getLatLng().lat;
          $scope.longitude = marker.getLatLng().lng;
          document.getElementById('latitude').value = $scope.latitude;
          document.getElementById('longitude').value = $scope.longitude;
          marker.setLatLng(e.latlng);
        } else {
          $rootScope.myMap.removeLayer(marker);
          marker = new L.Marker(e.latlng);
          $rootScope.myMap.addLayer(marker);
          $scope.latitude = marker.getLatLng().lat;
          $scope.longitude = marker.getLatLng().lng;
          document.getElementById('latitude').value = $scope.latitude;
          document.getElementById('longitude').value = $scope.longitude;
          marker.setLatLng(e.latlng);
        }
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
			let dataObj = {
				name : $scope.newPlaceName,
				category : $scope.newPlaceType,
				description : $scope.newPlaceDescription,
				point : {
					latitude : $scope.latitude,
					longitude : $scope.longitude
				}
			};

			let successCallback = function(response){
				$scope.submissionSuccess = true;
				setTimeout(function() {
					$scope.$apply(function() {
						$scope.submissionSuccess = false;
					});
				}, 5000);
		    };

		    let errorCallback = function(response){
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

		$scope.findById = function () {
	    	let successCallBack = function(response){
	    		$scope.latitude =  response.data.latitude;
	    		$scope.longitude =  response.data.longitude;
	    		console.log($scope.latitude);

	    		$rootScope.myMap.setView([$scope.latitude, $scope.longitude], 13);

				marker = new L.Marker([$scope.latitude, $scope.longitude]);
				$rootScope.myMap.addLayer(marker);
	    		marker.setLatLng([$scope.latitude, $scope.longitude]);
		    };

			$http.get(_contextPath + '/point/' + $routeParams.id).then(successCallBack);
		};

		if ($routeParams.id) {
			$scope.findById();
		}

    // -----START ADD Event-----
    $scope.addEventMenu = function() {
      $scope.addEventMenuIsOpen = true;
    };

    $scope.toggleAddEventMenu = function() {
      $scope.addEventMenuIsOpen = false;
    };

    $scope.resetAddEventForm = function(form) {
      $rootScope.myMap.removeLayer(marker);
    };

    $('.datepicker').pickadate({
      selectMonths: true, // Creates a dropdown to control month
      selectYears: 15 // Creates a dropdown of 15 years to control year
    });



    }
});
