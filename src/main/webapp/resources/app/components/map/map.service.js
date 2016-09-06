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