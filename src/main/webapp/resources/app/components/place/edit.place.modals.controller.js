'use strict';

angular
		.module('greenApp')
		.controller(
				'EditPlaceCtrl',
				["$scope", "$http", "$routeParams", "$route", function($scope, $http, $routeParams, $route) {
					$scope.update_name = function () {
						console.log("update");
				    	$scope.id = $routeParams.id;
				    		
			    	    var dataObj = {
			    	    	name: $scope.name,
					    };
			    	    
			    	    var res = $http.put(_contextPath + '/api/place/' + $scope.id, dataObj);
			    	    console.log(dataObj);
			    	    res.success(function(data, status, headers, config) {
			    		});
					};
					
					$scope.update_description = function () {
						console.log("update");
				    	$scope.id = $routeParams.id;
				    		
			    	    var dataObj = {
			    	        description: $scope.description,
					    };
			    	    
			    	    var res = $http.put(_contextPath + '/api/place/' + $scope.id, dataObj);
			    	    console.log(dataObj);
			    	    res.success(function(data, status, headers, config) {
			    		});
					};
				}]);















/*
'use strict';

angular
		.module('greenApp')
		.controller('EditPlaceController'
		.component(
				'editableplace',
				{
					templateUrl : _contextPath +'/WEB-INF/views/main.jsp',
					controller : function($scope, $http, $routeParams, $location, $route) {
						
						$scope.update = function () {
							console.log("update");
					    	$scope.id = $routeParams.id;
					    		
				    	    var dataObj = {
				    	    	//id: $routeParams.id,
				    	    	name: $scope.name,
				    	        description: $scope.description,
						    };
				    	    
				    	    var res = $http.put(_contextPath + '/api/place/' + $scope.id, dataObj);
				    	    console.log(dataObj);
				    	    res.success(function(data, status, headers, config) {
				    		});
						};	
					}
				}));
*/