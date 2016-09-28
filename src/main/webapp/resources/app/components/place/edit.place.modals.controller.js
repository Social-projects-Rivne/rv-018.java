'use strict';

angular
		.module('greenApp')
		.controller(
				'EditPlaceCtrl',
				["$scope", "$http", "$routeParams", "$route", function($scope, $http, $routeParams, $route) {
					
					/*$scope.childmethod = function() {
			            $rootScope.$emit("CallThatParentMethod", {});
			        }*/
					
					$scope.update_name = function () {
						/*$rootScope.$emit("CallThatParentMethod", {});*/
						console.log("update");
				    		
			    	    var dataObj = {
			    	    	name: $scope.name,
					    };
			    	    
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
			    	    
			    	    $http.put(_contextPath + '/api/place/' + 1, dataObj).then(successCallback, errorCallback);
			    	    console.log(dataObj);
			    	    
					};
					
					$scope.update_description = function () {
						console.log("update");
				    	$scope.id = $routeParams.id;
				    		
			    	    var dataObj = {
			    	        description: $scope.description,
					    };
			    	    
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
			    	    
			    	    $http.put(_contextPath + '/api/place/' + $scope.$id, dataObj).then(successCallback, errorCallback);
			    	    console.log(dataObj);
			    	    
					 };
				}]);