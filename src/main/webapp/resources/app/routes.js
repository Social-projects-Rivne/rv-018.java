angular.module('greenTourism').config(function($routeProvider) {
  $routeProvider
    .when('/login', {
      template: '<login></login>'
    }).otherwise('/');
});
