'use strict';

angular.module('greenApp').config(function($routeProvider) {	
	$routeProvider.when('/profile/:id', {
		template : '<profile></profile>'
	}).when('/user/:userId', {
		template : '<user></user>'
	}).when('/map', {
		template : '<map></map>'
	}).when('/map/:searchplace', {
		template : '<map></map'
	}).when('/map/place/:id', {
		template : '<map></map>'
	});
});
