angular.module('greenApp')
	.service('CalendarIsOpen', function() {
		var calendarIsOpen;
		
		this.setCalendarIsOpen = function(value) {
			this.calendarIsOpen = value;
		}

		this.getCalendarIsOpen = function() {
			return this.calendarIsOpen;
		}
	})
	.service('CalendarButtonIsShown', function() {
		var calendarButtonIsShown;
		
		this.setCalendarButtonIsShown = function(value) {
			this.calendarButtonIsShown = value;
		}

		this.getCalendarButtonIsShown = function() {
			return this.calendarButtonIsShown;
		}
	})
	.service('CalendarDateRangeIsChosen', function() {
		var calendarDateRangeIsChosen = false;
		
		this.setCalendarDateRangeIsChosen = function(value) {
			this.calendarDateRangeIsChosen = value;
		}

		this.getCalendarDateRangeIsChosen = function() {
			return this.calendarDateRangeIsChosen;
		}
	})
	.service('MapMarkersArray', function() {
		var mapMarkersArrayParam = [];
		
		this.setMapMarkersArray = function(value) {
			this.mapMarkersArrayParam = value;
		}

		this.getMapMarkersArray = function() {
			return this.mapMarkersArrayParam;
		}
	});

