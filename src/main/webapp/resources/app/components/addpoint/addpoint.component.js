'use strict';

angular.
  module('greenApp').
  component('addpoint', {
	  templateUrl: 'resources/app/components/addpoint/addpoint.template.html',
	  controller: function($scope) {
		  
		  var map = L.map('mapid').setView([51.505, -0.09], 13);
	    	
	    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
				maxZoom: 18,
				attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
					'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
					'Imagery � <a href="http://mapbox.com">Mapbox</a>',
				id: 'mapbox.streets'
			}).addTo(map);
	    	
	    	var new_event_marker;
	        map.on('click', function(e){
	        	if(typeof(new_event_marker)==='undefined'){
	        		new_event_marker = new L.marker(e.latlng,{ draggable: true});
	        		new_event_marker.addTo(map);   
	        		
	        	} else{
	        		document.getElementById('latitude').value = new_event_marker.getLatLng().lat;
	        		document.getElementById('longitude').value = new_event_marker.getLatLng().lng;
	        		new_event_marker.setLatLng(e.latlng);
	        	}
	        	
	        }); 
	        
		 $scope.addPlaceMenu =function(){
		      $scope.addPlaceMenuIsOpen = true;
		  };
		  
		  
		  $scope.toggleAddPlaceMenu =function(placeMaker){
		      $scope.addPlaceMenuIsOpen = false;
		  };
		 
		}
  });
