'use strict';

angular.module('greenApp')
.component('map', {
  templateUrl: _contextPath + '/resources/app/components/map/map.template.html',
  controller: function($rootScope, $scope, $http, $routeParams, CalendarIsOpen, CalendarButtonIsShown, $templateCache) {

    $rootScope.mopen();

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

      $scope.showOrHideCalendar = function() {
        if ($scope.singletonCalendarIsOpen.getCalendarIsOpen())
        $scope.singletonCalendarIsOpen.setCalendarIsOpen(false);
        else
        $scope.singletonCalendarIsOpen.setCalendarIsOpen(true);
      };

      let marker;

      $scope.addPlaceMenu = function() {
        $scope.addPlaceMenuIsOpen = true;
        $rootScope.myMap.on('click',function(e) {
          if (typeof(marker) === 'undefined') {
            marker = new L.Marker(e.latlng);
            $rootScope.myMap.addLayer(marker);
            $scope.latitude = marker.getLatLng().lat;
            $scope.longitude = marker.getLatLng().lng;
            //document.getElementById('latitude').value = $scope.latitude;
            //document.getElementById('longitude').value = $scope.longitude;
            marker.setLatLng(e.latlng);
          } else {
            $rootScope.myMap.removeLayer(marker);
            marker = new L.Marker(e.latlng);
            $rootScope.myMap.addLayer(marker);
            $scope.latitude = marker.getLatLng().lat;
            $scope.longitude = marker.getLatLng().lng;
            //document.getElementById('latitude').value = $scope.latitude;
            //document.getElementById('longitude').value = $scope.longitude;
            marker.setLatLng(e.latlng);
          }
              $scope.$apply();
        }
      )
    };

    $scope.toggleAddPlaceMenu = function() {
      $scope.addPlaceMenuIsOpen = false;
      $rootScope.myMap.off('click');
      $scope.newPlaceName = "";
      $scope.newPlaceType = "";
      $scope.newPlaceDescription = "";
      $scope.latitude = "";
      $scope.longitude = "";
      $scope.newPlacePhoto = "";
    };

    $scope.hideButtonAddPlace = function(){
      $scope.addButtonAddPlace = false;
    }

    $scope.togleButtonAddPlace = function() {
      $scope.addButtonAddPlace = true;
    };

    $scope.createNewPlace = function(form) {
      let dataObj = {
        place : {
          name : $scope.newPlaceName,
          description : $scope.newPlaceDescription,
          point : {
            latitude : $scope.latitude,
            longitude : $scope.longitude
          },
          category : {
            name : $scope.newPlaceType,
            tableType : "place"
          },
        },
        attachment: {
          fileSrc: $scope.newPlacePhoto
        },
        tableType : "place",
      };

    console.log(dataObj);

    let successCallback = function(response){
      Materialize.toast('Place successfully added!', 2000);
      setTimeout(function () {
        $scope.$apply(function () {
          $scope.addPlaceMenuIsOpen = false;
          $rootScope.myMap.off('click');
          $scope.newPlaceName = "";
          $scope.newPlaceType = "";
          $scope.newPlaceDescription = "";
          $scope.latitude = "";
          $scope.longitude = "";
          $scope.newPlacePhoto = "";
          $scope.addButtonAddPlace = true;
        });
      }, 50);
    };

    let errorCallback = function(response){
      Materialize.toast('Something wrong. Please try again!', 2000);
    };

    $http.post(_contextPath + "/api/gallery/", dataObj).then(successCallback, errorCallback);
  };

  $scope.places = ["Places of interest", "Places near water", "Recreation area"];

  $scope.resetAddPlaceForm = function(form) {
    if (marker) {
      $rootScope.myMap.removeLayer(marker);
    }
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
    $rootScope.myMap.on('click',function(e) {

      if (typeof(marker) === 'undefined') {
        marker = new L.Marker(e.latlng);
        $rootScope.myMap.addLayer(marker);
        $scope.latitudeE = marker.getLatLng().lat;
        $scope.longitudeE = marker.getLatLng().lng;
        //document.getElementById('latitudE').value = $scope.latitudeE;
        //document.getElementById('longitudE').value = $scope.longitudeE;
        marker.setLatLng(e.latlng);
      } else {
        $rootScope.myMap.removeLayer(marker);
        marker = new L.Marker(e.latlng);
        $rootScope.myMap.addLayer(marker);
        $scope.latitudeE = marker.getLatLng().lat;
        $scope.longitudeE = marker.getLatLng().lng;
        //document.getElementById('latitudE').value = $scope.latitudeE;
        //document.getElementById('longitudE').value = $scope.longitudeE;
        marker.setLatLng(e.latlng);
      }
          $scope.$apply();
    }
  )
};

$scope.toggleAddEventMenu = function() {
  $scope.addEventMenuIsOpen = false;
  $rootScope.myMap.off('click');
  $scope.newEventName = "";
  $scope.newEventType = "";
  $scope.newEventDescription = "";
  $scope.newStartDate = "";
  $scope.newEndDate = "";
  $scope.latitudeE = "";
  $scope.longitudeE = "";
  $scope.newEventPhoto = "";
};

$scope.resetAddEventForm = function(form) {
  if (marker) {
    $rootScope.myMap.removeLayer(marker);
  }
};

$scope.createNewEvent = function(form) {
  let dataObj = {
    event : {
      name : $scope.newEventName,
      description : $scope.newEventDescription,
      dateStart : $scope.newStartDate,
      dateEnd : $scope.newEndDate,
      point : {
        latitude : $scope.latitudeE,
        longitude : $scope.longitudeE
      },
      category : {
        name : $scope.newEventType,
        tableType : "event"
      },
    },
    attachment: {
      fileSrc: $scope.newEventPhoto
    },
    tableType : "event",
  };
  console.log(dataObj);

  let successCallback = function(response){
    Materialize.toast('Event successfully added!', 2000);
    setTimeout(function () {
      $scope.$apply(function () {
        $scope.addEventMenuIsOpen = false;
        $rootScope.myMap.off('click');
        $scope.newEventName = "";
        $scope.newEventType = "";
        $scope.newEventDescription = "";
        $scope.newStartDate = "";
        $scope.newEndDate = "";
        $scope.latitudeE = "";
        $scope.longitudeE = "";
        $scope.newEventPhoto = "";
        $scope.addButtonAddPlace = true;
      });
    }, 50);
  };

  let errorCallback = function(response){
    Materialize.toast('Something wrong. Please try again!', 2000);
  };

  $http.post(_contextPath + "/api/gallery/", dataObj).then(successCallback, errorCallback);
};

$scope.events = ["Sport competition", "Festival", "Meeting"];

    $scope.showPlaceButton = function(){
      $scope.addPlace = true;
    }

    $scope.hidePlaceButton = function(){
      $scope.addPlace = false;
    }

    $scope.toggleAddPlaceMenuSmallScreen = function() {
      $scope.addPlaceMenuIsOpen = false;
    };

    $scope.showEventButton = function(){
      $scope.addEvent = true;
    }

    $scope.hideEventButton = function(){
      $scope.addEvent = false;
    }

    $scope.toggleAddEventMenuSmallScreen = function() {
      $scope.addEventMenuIsOpen = false;
    };
  }
});
