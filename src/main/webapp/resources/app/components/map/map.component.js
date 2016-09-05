'use strict';

angular.module('greenApp')
  .component('map', {
    templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
    controller: function($rootScope, $scope, $http, $routeParams) {
    	var mymap = L.map('mapid').setView([ 50.619900, 26.251617 ], 13);
    	$rootScope.myMap = mymap;
    	
    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',{
			maxZoom : 18,
			attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
			+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
			+ 'Imagery � <a href="http://mapbox.com">Mapbox</a>',
			id : 'mapbox.streets'}).addTo(mymap);
		
		var marker;
		mymap.on('click',function(e) {
			marker = new L.Marker(e.latlng, {draggable:true});
			mymap.addLayer(marker);
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
			mymap.removeLayer(marker);
		};
		
		$rootScope.$emit('initMarkerController', {});
		
/*		
		var successCallBacks = function(response){
    		//$scope.places = response.data;
    		console.log (response) ;
    		
	    };
	    $http.get(_contextPath + '/api/place/user/' + $routeParams.id).then(successCallBacks);
	    
		*/
//		
//		var successCallBack = function(response){
//    		$scope.places = response.data;
//    		console.log (response.data) ;
//	    };
//	    
//		$http.get(_contextPath + '/api/place/user/' + $routeParams.id).then(successCallBack);
		
		
		$scope.findById = function () {
	    	    	
	    	var successCallBack = function(response){
	    		$scope.places = response.data;
	    		console.log (response.data) ;
	    		
	    		$scope.name = response.data.name;
	    		
	    		console.log ('$scope.name ' +  $scope.name);
//	    		console.log ('scope.latitude ' + $scope.place.latitude);
//	    		console.log ('$scope.longitude ' +  $scope. place.longitude);
	    		
	    		$scope.latitude =  50.619900;
	    		$scope.longitude =  26.251617; 
	    		//marker = L.marker([$scope.latitude, $scope.longitude], {icon: greenIcon});
	    		
	    		mymap.setView([$scope.latitude, $scope.longitude], 14);
	    		
				marker = new L.Marker([$scope.latitude, $scope.longitude]);
				mymap.addLayer(marker);
	    		marker.setLatLng([$scope.latitude, $scope.longitude]);
		    };
			
			$http.get(_contextPath + '/api/place/user/' + $routeParams.id).then(successCallBack);
		};

		if ($routeParams.id) { 
			$scope.findById(); 
		} 
	
    }
});
