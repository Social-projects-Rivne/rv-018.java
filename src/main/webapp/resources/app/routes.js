'use strict';

angular.module('greenApp').config(function($routeProvider) {	
	$routeProvider.when('/login', {
		template : '<login></login>'
	}).when('/profile', {
		template : '<profile></profile>'
	}).when('/user/:userId', {
		template : '<user></user>'
	}).when('/map/:search', {
		template : '<map></map>'
	}).when('/map', {
		template : '<map></map>'
	});
});
