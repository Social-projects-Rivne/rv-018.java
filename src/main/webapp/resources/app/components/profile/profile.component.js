'use strict';

angular.module('greenApp').
component('profile', {
	templateUrl : _contextPath +  '/resources/app/components/profile/profile.template.html',
	controller : function($scope, $http) {
		$scope.findById = function () {
	    	// update only if id chosen
	    	if (!$scope.id) {
	    		return;
	    	}
	    	$scope.errorMessage = "";
	    	
	    	var successCallBack = function(response){
	    		$scope.username = response.data.username;
				$scope.email = response.data.email;
				$scope.firstName = response.data.firstName;
				$scope.lastName = response.data.lastName;
		    };
			
			$http.get(_contextPath + '/user/' + $scope.id).then(successCallBack);
		};
		
		$scope.update = function () {
	    	// update only if id is specified
	    	if ($scope.id == undefined) {
	    		$scope.errorMessage = "Please, select the id of existing user and try again!"
	    		return;
	    	}
	        
	    	var dataObj = {
	            id: $scope.id,
	            username: $scope.username,
	            email: $scope.email,
	            firstName: $scope.firstName,
	            lastName: $scope.lastName
	        };
	        
			var res = $http.put(_contextPath + '/user/'+$scope.id, dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	}
});
