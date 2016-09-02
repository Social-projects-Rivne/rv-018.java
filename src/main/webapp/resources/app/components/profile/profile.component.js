'use strict';

angular.module('greenApp').
component('profile', {
	templateUrl : _contextPath +  '/resources/app/components/profile/profile.template.html',
	controller : function($scope, $http, $routeParams) {
	    	
		var successCallBack = function(response){
    		$scope.name = response.data.name;
    		$scope.placeFoto = response.data.placeFoto;
			
	    };
	    
		$http.get(_contextPath + '/api/place/' + $routeParams.id).then(successCallBack);
		
		
		var successCallBack = function(response){
    		$scope.username = response.data.username;
			$scope.email = response.data.email;
			$scope.firstName = response.data.firstName;
			$scope.lastName = response.data.lastName;
			$scope.socialAccount = response.data.socialAccount;
			$scope.userpic = response.data.userpic;
			
	    };
	    
	    $scope.update = function () {
	    	// update only if id is specified
	    	/*if ($scope.id == undefined) {
	    		$scope.errorMessage = "Please, select the id of existing user and try again!"
	    		return;
	    	}*/
			$scope.id = $routeParams.id;
	        
	    	var dataObj = {
	    			name: $scope.name,
	    			userpic: $scope.placeFoto
	        };
	        
			var res = $http.put(_contextPath + '/api/place/'+ $scope.id, dataObj);
			res.success(function(data, status, headers, config) {
				// your code in case of success is here
			});
		};
	    
		$http.get(_contextPath + '/user/' + $routeParams.id).then(successCallBack);
		
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
			
			$http.get(_contextPath + '/user/' + $routeParams.id).then(successCallBack);
		};
		
		$scope.update = function () {
	    	// update only if id is specified
	    	/*if ($scope.id == undefined) {
	    		$scope.errorMessage = "Please, select the id of existing user and try again!"
	    		return;
	    	}*/
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
		  // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		  $('.modal-trigger').leanModal();
	});

	$('.modal-trigger').leanModal({
		  dismissible: true, // Modal can be dismissed by clicking outside of the modal
		  opacity: .1, // Opacity of modal background
		  in_duration: 300, // Transition in duration
		  out_duration: 200, // Transition out duration
		  starting_top: '4%', // Starting top style attribute
		  ending_top: '10%', // Ending top style attribute
		  ready: function() { alert('Ready'); }, // Callback for Modal open
		  complete: function() { alert('Closed'); } // Callback for Modal close
		}
		);
		
		$scope.ImageUrl="http://content.screencast.com/users/kazakov/folders/Snagit/media/9777b814-7f03-40b4-bafd-c64a0d39e95c/08.31.2016-23.23.png";
	}
});
