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
	}).when('/home', {
		templateUrl : _contextPath + '/resources/app/components/home/home.template.html'
	}).otherwise('/', {
		redirectTo:  _contextPath + '/resources/app/components/home/home.template.html'
	});
});
