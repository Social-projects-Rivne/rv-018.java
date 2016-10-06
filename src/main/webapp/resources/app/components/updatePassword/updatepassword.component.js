'use strict';

angular.module('greenApp').
component('updatepassword', {
	templateUrl : _contextPath +  '/resources/app/components/updatePassword/updatepassword.template.html',
	controller : function($scope, $http, $routeParams) {
	    	
		/*var successCallBack = function(response){
    		$scope.token = response.data.token;
    		console.log($scope.token);
	    };
	    
	    $http.get(_contextPath + '/user/' + $routeParams.token).then(successCallBack);*/
		
		
		$scope.updatePassword = function () {
			$scope.token = $routeParams.token;
			console.log($routeParams.token);
			
	            /*password: $scope.password,*/
	    	console.log($scope.password);
	        
			var res = $http.put(_contextPath + '/user/savePassword/' + $scope.token, {password: $scope.password});
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	}
});
