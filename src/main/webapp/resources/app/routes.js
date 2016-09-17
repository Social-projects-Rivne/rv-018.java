'use strict';

angular.module('greenApp').config(function($routeProvider) {	
	$routeProvider.when('/profile/:id', {
		template : '<profile></profile>'
	}).when('/user/:userId', {
		template : '<user></user>'
	}).when('/map', {
		template : '<map></map>'
	}).when('/place/:placeId', {
		template : '<place></place>'		
	}).when('/place/edit/:placeId', {
		template : '<editplace></editplace>'
	}).when('/map/:searchplace', {
		template : '<map></map'
	}).when('/map/place/:id', {
		template : '<map></map>'
	});
});
