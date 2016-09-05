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
