'use strict';

angular.module('greenApp')
	.controller('markerCtrl', function($rootScope, $scope, $http, $log){
		var markersArray = [];
    	var currentlySelectedTabInnerHtml = "Places";
		
		$rootScope.$on('initMarkerController', function() {
//	    	L.MakiMarkers.accessToken = "pk.eyJ1IjoiYm1ha3MiLCJhIjoiY2lzZzdrdzhsMDA1MDJvcGZ1ejI5eGlwaCJ9.X8-116vYRGTlIw2axoJmQg";
//	    	var icon = L.MakiMarkers.icon({icon: "marker-stroked", color: "#b0b", size: "m"});

			$rootScope.myMap.on('moveend', myMapMoveEndEventFunc());
			
			// Check currently selected tab
			$('ul.tabs').on('click', 'a', function(e) {
				currentlySelectedTabInnerHtml = e.currentTarget.innerHTML;
				
				if (markersArray.length > 0)
					angular.forEach(markersArray, function(marker, key){
						$rootScope.myMap.removeLayer(marker);
					})
					
				markersArray = [];
				myMapMoveEndEventFunc();
			});
		})
		
		var myMapMoveEndEventFunc = function() {
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

				if (markersArray.length > 0)
					angular.forEach(markersArray, function(marker, key){
						$rootScope.myMap.removeLayer(marker);
					})
				markersArray = [];
				
				angular.forEach(points, function(point, key){
					markersArray.push(L.marker([point.latitude, point.longitude]).addTo($rootScope.myMap));
				})
			}, function(error){
				$scope.progressBarVision = false;
				$rootScope.error = error;
				$log.info(error);
			});

			angular.forEach(markersArray, function(marker, key){
				$rootScope.myMap.addLayer(marker);
			})
		}
});