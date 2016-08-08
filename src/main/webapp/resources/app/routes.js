'use strict';

angular.module('greenApp').config(function($routeProvider) {
	$routeProvider.when('/login', {
		template : '<login></login>'
	}).when('/profile', {
		template : '<profile>'
	}).when('/map', {
		template : '<map>'
	}).otherwise('/');
});