$('.sideNavMenuButton').sideNav({
      menuWidth: 300, // Default is 240
      closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
    }
  );

angular.module('greenApp')
.service('topMarginSharedValue', function () {
    var topMarginValue;

    return {
        getTopMarginValue: function () {
            return topMarginValue;
        },
        setTopMarginValue: function(value) {
        	topMarginValue = value;
        }
    };
});