angular.module('greenTourism').config(function($routeProvider) {
  $routeProvider
    .when('/login', {
      template: '<login></login>'
    }).when('/register', {
		template: '<register></register>'	  
    }).otherwise('/');
});




