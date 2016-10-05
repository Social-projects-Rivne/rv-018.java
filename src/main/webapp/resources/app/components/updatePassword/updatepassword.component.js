'use strict';

angular.module('greenApp').
component('updatepassword', {
	templateUrl : _contextPath +  '/resources/app/components/updatePassword/updatepassword.template.html',
	controller : function($scope, $http, $routeParams) {
	    	
		$scope.updatePassword = function () {
			email = $scope.email;
	        
	    	var dataObj = {
	            password: $scope.password,
	        };
	        
			var res = $http.put(_contextPath + '/user/savePassword', dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	}
});
