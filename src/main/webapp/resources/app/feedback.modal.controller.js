'use strict';

angular
		.module('greenApp')
		.controller(
				'PlaceFeedbackController',
				["$scope", "$http", "$routeParams", "$route", "$localStorage", function($scope, $http, $routeParams, $route, $localStorage) {
					
				   $scope.addText = function() {
					   if (!$scope.text) {
						   return;
					   }
					  
					   if ($localStorage.message == 'logout') {
						   
					    $http({
					    	method: 'POST',
					    	url: _contextPath + "/api/comment", 
					    	headers: { 'Authorization': $localStorage.authorization },
					    	data: {
					    		place:{
					    		  id: $scope.id,
					    		},
					    		body: $scope.text,
						          tableType : "place",
						    }
					    }).then(function(response) {
					    	Materialize.toast('Feedback successfully added!', 2000);
						      $scope.submissionSuccess = true;
						      setTimeout(function() {
						        $scope.$apply(function() {
						          $scope.submissionSuccess = false;
						        });
						      }, 50);
					    }, function(error) {
					    	Materialize.toast('Something wrong. Please try again!', 2000);
					    });
					       } else 
						   Materialize.toast('Please log in first. Only logged in user can add Feedback!', 4000);
						   setTimeout(function () {
						        $scope.$apply(function () {
						        	$scope.text = "";
						        	$scope.submissionSuccess = false;
						        });
						    }, 50);
					 };

					  $scope.addImage = function() {
						  
						  if ($localStorage.message == 'logout') {
							  
							  $http({
							    	method: 'POST',
							    	url: _contextPath + "/api/gallery/",
							    	headers: { 'Authorization': $localStorage.authorization },
							    	data: {
							    		place:{
							    		  id: $scope.id,
							    		},
							    		attachment: {
								            fileSrc: $scope.newPlacePhoto
								        },
								          tableType : "place",
								    }
								}).then(function(response){
							  console.log("Image added");
						      setTimeout(function () {
						        $scope.$apply(function () {
						          $scope.newPlacePhoto = "";
						        });
						      }, 50);
						   }, function(error){
					    	 console.log("Error, image not added");
					         });
						} 
					  };
					  
					  $scope.addEventText = function() {
						   if (!$scope.text) {
							   return;
						   }
						  
						   if ($localStorage.message == 'logout') {
							   
						    $http({
						    	method: 'POST',
						    	url: _contextPath + "/api/comment", 
						    	headers: { 'Authorization': $localStorage.authorization },
						    	data: {
						    		event:{
						    		  id: $scope.id,
						    		},
						    		body: $scope.text,
							          tableType : "event",
							    }
						    }).then(function(response) {
						    	Materialize.toast('Feedback successfully added!', 2000);
							      $scope.submissionSuccess = true;
							      setTimeout(function() {
							        $scope.$apply(function() {
							          $scope.submissionSuccess = false;
							        });
							      }, 50);
						    }, function(error) {
						    	Materialize.toast('Something wrong. Please try again!', 1000);
						    });
						       } else 
							   Materialize.toast('Please log in first. Only logged in user can add Feedback!', 1000);
							   setTimeout(function () {
							        $scope.$apply(function () {
							        	$scope.text = "";
							        	$scope.submissionSuccess = false;
							        });
							    }, 50);
						 };

						  $scope.addEventImage = function() {
							  
							  if ($localStorage.message == 'logout') {
								  
								  $http({
								    	method: 'POST',
								    	url: _contextPath + "/api/gallery/",
								    	headers: { 'Authorization': $localStorage.authorization },
								    	data: {
								    		event:{
								    		  id: $scope.id,
								    		},
								    		attachment: {
									            fileSrc: $scope.newEventPhoto
									        },
									          tableType : "event",
									    }
									}).then(function(response){
								  console.log("Image added");
							      setTimeout(function () {
							        $scope.$apply(function () {
							          $scope.newEventPhoto = "";
							        });
							      }, 50);
							   }, function(error){
						    	 console.log("Error, image not added");
						         });
							} 
						  };
	}]);

