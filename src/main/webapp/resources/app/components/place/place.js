$('.sideNavMenuButton').sideNav({
      menuWidth: 300, // Default is 240
      closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
    }
  );

angular.module('greenApp')
.service('mapTopValue', function () {
    var mapTopValue;

    return {
        getMapTopValue: function () {
            return mapTopValue;
        },
        setMapTopValue: function(value) {
        	mapTopValue = value;
        }
    };
});
/*.service('mapTopValue', function () {
    var mapTopValue;

    return {
        getMapTopValue: function () {
            return mapTopValue;
        },
        setMapTopValue: function(value) {
        	mapTopValue = value;
        }
    };
})*/