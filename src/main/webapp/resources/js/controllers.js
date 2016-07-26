'use strict';

var greenApp = angular.module('greenApp', []);
greenApp.controller("TestCtrl", function($scope) {
	$scope.test = "Angular works correctly!";
});