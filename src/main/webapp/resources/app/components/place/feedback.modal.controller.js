'use strict';

angular
		.module('greenApp')
		.controller(
				'PlaceFeedbackController',
				["$scope", "$http", "$routeParams", "$route", function($scope, $http, $routeParams, $route) {
					//$scope.username = "John Smith";
					$scope.findById = function() {
						// update only if id chosen
						if (!$scope.id) {
							return;
						}
						$scope.errorMessage = "";

						var successCallBack = function(response) {
							$scope.username = response.data.user;
							$scope.userpicture = response.data.user;
						};

						var failCallback = function(response) {
							console.log(response);
						};

						$http.get(_contextPath + '/api/place/' + $scope.id).then(successCallBack,failCallback);
					};
					
					$scope.addText = function() {
					      let dataObj = {
					        feedback : {
					          body : $scope.text,
					          date : $scope.date,
					        },
					      };

					    console.log(dataObj);

					    let successCallback = function(response){
					      $scope.submissionSuccess = true;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionSuccess = false;
					        });
					      }, 5000);
					    };

					    let errorCallback = function(response){
					      $scope.submissionError = true;
					      $scope.submissionSuccess = false;
					      setTimeout(function() {
					        $scope.$apply(function() {
					          $scope.submissionError = false;
					        });
					      }, 5000);
					    };

					    $http.post(_contextPath + '/api/comment/' + $routeParams.id, dataObj).then(successCallback, errorCallback);
					    
					    $scope.id = $routeParams.placeId;
					    $scope.findById();
					  };
				}]);
