angular.module('greenApp')
	.controller('calendarCtrl', function($rootScope ,$scope, $filter, $http, $q, $log, CalendarIsOpen) {

    $scope.dayFormat = "d";
    $scope.singletonCalendarIsOpen = CalendarIsOpen;

    $scope.selectedDate = [];

    $scope.firstDayOfWeek = 0;
    $scope.setDirection = function(direction) {
      $scope.direction = direction;
      $scope.dayFormat = direction === "vertical" ? "EEEE, MMMM d" : "d";
    };

    $scope.dayClick = function(date) {
    	if ($scope.selectedDate.length == 2) {

    		var startDate;
    		var endDate;
    		var markersArray = [];

    		$scope.singletonCalendarIsOpen.setCalendarIsOpen(false);

    		if ($scope.selectedDate[0] > $scope.selectedDate[1]) {
    			endDate = $filter("date")($scope.selectedDate[0], "EEE MMM dd yyyy HH:mm:ss");
    			startDate = $filter("date")($scope.selectedDate[1], "EEE MMM dd yyyy HH:mm:ss");
    		} else {
    			endDate = $filter("date")($scope.selectedDate[1], "EEE MMM dd yyyy HH:mm:ss");
    			startDate = $filter("date")($scope.selectedDate[0], "EEE MMM dd yyyy HH:mm:ss");
    		}
			$http({
				method: 'GET',
				url: _contextPath + '/api/' 
									+ 'event'  
									+ '/pointBetweenDates' 
									+ '?start-date=' + startDate 
									+ '&end-date=' + endDate
			})
			.then(function(response){
				$scope.selectedDate = [];
				$log.info(response);
				
				var points = response.data;

				if (markersArray.length > 0)
					angular.forEach(markersArray, function(marker, key){
						$rootScope.myMap.removeLayer(marker);
					})
					markersArray = [];
					
				var markerIcon = null;
				var GreenIcon = L.Icon.Default.extend ({
					options: {
						iconUrl: 'http://www.clker.com/cliparts/G/e/o/0/f/m/map-pin-red-hi.png'
					}
				});
				markerIcon = new GreenIcon(); 					
				
				angular.forEach(points, function(point, key){
					markersArray.push(L.marker([point.latitude, point.longitude], {icon: markerIcon}).addTo($rootScope.myMap));
				})
			}, function(error){
				$scope.selectedDate = [];
				$log.info(response);
			});

    	} 
      $scope.msg = "You clicked " + $filter("date")(date, "EEE MMM dd yyyy HH:mm:ss");
      $log.info("You clicked " + $filter("date")(date, "MMM d, y h:mm:ss a Z"));
    };

    $scope.prevMonth = function(data) {
      $scope.msg = "You clicked (prev) month " + data.month + ", " + data.year;
      $log.info("You clicked (prev) month " + data.month + ", " + data.year);
    };

    $scope.nextMonth = function(data) {
      $scope.msg = "You clicked (next) month " + data.month + ", " + data.year;
      $log.info("You clicked (next) month " + data.month + ", " + data.year);
    };

    $scope.tooltips = true;
});