'use strict';

angular.module('greenApp').config(function($routeProvider) {	
	$routeProvider.when('/login', {
		template : '<login></login>'
	}).when('/profile', {
		template : '<profile>'
	}).when('/user/:userId', {
		template : '<user></user>'
	}).when('/map', {
		template : '<map>'
	}).otherwise('/');
});
