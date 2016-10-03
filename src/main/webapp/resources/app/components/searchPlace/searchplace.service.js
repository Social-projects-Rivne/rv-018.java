angular.module('greenApp')
	.service('CurrentlySelectedTab', function() {
		var currentlySelectedTab;
		
		this.setCurrentlySelectedTab = function(value) {
			this.currentlySelectedTab = value;
		}

		this.getCurrentlySelectedTab = function() {
			return this.currentlySelectedTab;
		}
	});
