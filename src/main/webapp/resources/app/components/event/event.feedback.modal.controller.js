'use strict';

angular
		.module('greenApp')
		.controller(
				'EventFeedbackController',
				["$scope", "$http", "$routeParams", "$route", function($scope, $http, $routeParams, $route) {
					
					$scope.addText = function() {
					      var dataObj = {
					          body : $scope.text
					      };

					    console.log(dataObj);
					    console.log($scope.text);

					    var successCallback = function(response){
					        Materialize.toast('Feedback successfully added!', 5000);
					      $scope.submissionSuccess = true;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionSuccess = false;
					        });
					      }, 50);
					    };

					    var errorCallback = function(response){
					    	Materialize.toast('Something wrong. Please try again!', 5000);
					      console.log(response);
					      $scope.submissionError = true;
					      $scope.submissionSuccess = false;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionError = false;
					        });
					      }, 50);
					    };

					    $http.post(_contextPath + '/api/comment/', dataObj).then(successCallback, errorCallback);
					    
					  };
					  
					  $scope.addImage = function(form) {
					      let dataObj = {
					        attachment: {
					          fileSrc: $scope.newEventPhoto
					        },
					      };

					    console.log(dataObj);

					    let successCallback = function(response){
					    	console.log("Image added");
					      setTimeout(function () {
					        $scope.$apply(function () {
					          $scope.newEventPhoto = "";
					        });
					      }, 50);
					    };

					    let errorCallback = function(response){
					    	console.log("No image added");
					    };

					    $http.post(_contextPath + "/api/gallery/", dataObj).then(successCallback, errorCallback);
					  };
				}]);
