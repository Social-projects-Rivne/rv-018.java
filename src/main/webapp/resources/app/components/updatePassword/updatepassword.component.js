'use strict';

angular.module('greenApp').
component('updatepassword', {
	templateUrl : _contextPath +  '/resources/app/components/updatePassword/updatepassword.template.html',
	controller : function($scope, $http, $routeParams) {
	    	
		$scope.updatePassword = function () {
			$scope.id = $routeParams.id;
	        
	    	var dataObj = {
	            password: $scope.password,
	        };
	        
			var res = $http.put(_contextPath + '/api/user/changePassword', dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	}
});
