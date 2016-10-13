'use strict';

angular.module('greenApp').
component('profile', {
	templateUrl : _contextPath +  '/resources/app/components/profile/profile.template.html',
	controller : function($scope, $http, $routeParams) {
	    	
		var successCallBack = function(response){
    		$scope.places = response.data;
	    };
	    
		$http.get(_contextPath + '/api/place/profile/user/' + $routeParams.id).then(successCallBack);
		
		var successCallBack = function(response){
    		$scope.username = response.data.username;
			$scope.email = response.data.email;
			$scope.firstName = response.data.firstName;
			$scope.lastName = response.data.lastName;
			$scope.socialAccount = response.data.socialAccount;
			$scope.userpic = response.data.userpic ? response.data.userpic : 'images/No_Photo_icon.jpg';
	    };
	    
	    $http.get(_contextPath + '/user/profile/' + $routeParams.id).then(successCallBack);
	    
	    $scope.update = function () {
			$scope.id = $routeParams.id;
	        
	    	var dataObj = {  
	            username: $scope.name,
	            email: $scope.email,
	            firstName: $scope.firstName,
	            id: $routeParams.id,
	            lastName: $scope.lastName,
	            socialAccount: $scope.socialAccount,
	            userpic: $scope.userpic,
	            name: $scope.name,
	            description: $scope.description
	        };
	        
			var res = $http.put(_contextPath + '/api/place/'+ $scope.id, dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	    
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
				$scope.socialAccount = response.data.socialAccount;
				$scope.userpic = response.data.userpic;
		    };
			
			$http.get(_contextPath + '/user/profile/' + $routeParams.id).then(successCallBack);
		};
		
		$scope.update = function () {
			$scope.id = $routeParams.id;
	        
	    	var dataObj = {
	            username: $scope.username,
	            email: $scope.email,
	            firstName: $scope.firstName,
	            id: $routeParams.id,
	            lastName: $scope.lastName,
	            socialAccount: $scope.socialAccount,
	            userpic: $scope.userpic
	        };
	        
			var res = $http.put(_contextPath + '/user/'+$scope.id, dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
		
	$(document).ready(function(){
		  $('.modal-trigger').leanModal();
	});
	
	$scope.modalTrigger = function () { $('#edit-place').openModal() };
		
	$scope.ImageUrl="http://content.screencast.com/users/kazakov/folders/Snagit/media/9777b814-7f03-40b4-bafd-c64a0d39e95c/08.31.2016-23.23.png";
	
	/*User's events*/
	var successCallBack = function(responseev){
		$scope.events = responseev.data;
    };
    
	$http.get(_contextPath + '/api/event/profile/user/' + $routeParams.id).then(successCallBack);
	
	}
});
