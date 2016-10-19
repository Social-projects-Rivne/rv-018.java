'use strict';

angular.
    module('greenApp').
    component('searchplace', {
        templateUrl: _contextPath + '/resources/app/components/searchPlace/searchplace.template.html',
        controller:(function($scope, $http, $routeParams, $rootScope, $location, CurrentlySelectedTab) {
    	    $scope.address = [];
    	    $scope.counter = 0;
    	    
    	    var currentlySelectedTabInnerHtml = CurrentlySelectedTab.getCurrentlySelectedTab();
    	    var urlPath = currentlySelectedTabInnerHtml.toLowerCase().substring(0, currentlySelectedTabInnerHtml.length - 1);
    	    
            $http.get(_contextPath + 
            		'/api/' +
            		urlPath +
            		'/filter/name?name=' + $routeParams.name + '&ignorecase=true').then(function(response) {
                $scope.items = response.data;
                
                if($location.path() == '/map/searchplace')
             	    $scope.searchPlacesSidebarOpen = true;
                console.log($scope.items);
                if (response.data.length == 0)
             	    $scope.noSuchResultMessage = true;
                
                $scope.items.forEach($scope.getAttachments);
             	
                for (var i = 0; i < $scope.items.length; i++) {
             	    $scope.getAddressOfPoint($scope.items[i].point.latitude, $scope.items[i].point.longitude);

             	    $scope.items[i].address = $scope.address[i];
             	}
                
             });
            
            $scope.getAttachments = function(item, index) {
            	$http.get(_contextPath + 'api/attachment/find/' + 
            			urlPath +
            			'?id=' + item.id)
            		.then(function(response) {
            			item.attachments = response.data;
            			if (item.attachments.length == 0)
            				item.attachments[0] = {fileSrc: '\\resources\\images\\gps-icon.png'};
            		});
            }
            
            $scope.closePlacesSidebar = function() {
                $scope.searchPlacesSidebarOpen = false;
            }
            
            $scope.refocusMap = function(latitude, longitude) {
                $rootScope.myMap.panTo(new L.LatLng(latitude, longitude));
            }
            
            $scope.getAddressOfPoint = function(latitude, longitude) {
                $http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng=' + latitude + ',' + longitude)
             	    .then(function(response) {
             	        $scope.address.push(response.data.results[0].address_components[1].long_name 
                                            + ', ' + response.data.results[0].address_components[2].long_name);
             			
                        $scope.items[$scope.counter].address = $scope.address[$scope.counter];
                        $scope.counter++;
             	});
             }
        })
    });
