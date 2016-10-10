'use strict';

angular.module('greenApp').
component('updatepassword', {
	templateUrl : _contextPath +  '/resources/app/components/updatePassword/updatepassword.template.html',
	controller : function($scope, $http, $routeParams) {
		
		$scope.findByToken = function () {
			    let successCallBack = function(response){
			      $scope.user_id =  response.data.user.id;
			      $scope.token =  response.data.token;
			      $scope.user_email =  response.data.user.email;
			      $scope.user_firstName =  response.data.user.firstName;
			      $scope.user_lastName =  response.data.user.lastName;
			      $scope.user_socialAccount =  response.data.user.socialAccount;
			      $scope.user_userName =  response.data.user.userName;
			      
			      console.log(response.data);
			    };

			    $http.get(_contextPath + '/passwordResetToken/' + $routeParams.token).then(successCallBack);
		};
		
		if ($routeParams.token) {
		    $scope.findByToken();
		}
		
		$scope.updatePassword = function () {
			$scope.id = $scope.user_id;
	        
	    	var dataObj = {
	    		password: $scope.newPassword,
	    		id: $scope.user_id,
	    		username: $scope.user_userName,
	            email: $scope.user_email,
	            firstName: $scope.user_firstName,
	            lastName: $scope.user_lastName,
	            socialAccount: $scope.user_socialAccount,
	        };
	    	console.log(dataObj);
	        
			var res = $http.put(_contextPath + '/user/password/'+$scope.id, dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	}
});
