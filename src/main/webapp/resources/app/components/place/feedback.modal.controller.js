'use strict';

angular
		.module('greenApp')
		.controller(
				'PlaceFeedbackController',
				["$scope", "$http", "$routeParams", "$route", function($scope, $http, $routeParams, $route) {
					
					/*$scope.singletonFeedbackModalIsOpen = FeedbackModalIsOpen;*/
					
					$scope.addText = function() {
					      var dataObj = {
					        feedback : {
					          body : $scope.text,
					          date : $scope.date,
					        },
					      };

					    console.log(dataObj);
					    console.log($scope.date);

					    var successCallback = function(response){
					      $scope.submissionSuccess = true;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionSuccess = false;
					        });
					      }, 5000);
					    };

					    var errorCallback = function(response){
					      console.log(response);
					      $scope.submissionError = true;
					      $scope.submissionSuccess = false;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionError = false;
					        });
					      }, 5000);
					    };

					    $http.post(_contextPath + '/api/comment/', dataObj).then(successCallback, errorCallback);
					    
					  };
					  
				}]);
