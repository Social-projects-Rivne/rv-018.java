'use strict';

angular.module('greenApp')
.controller('EditPlaceController')
  .component('editableplace', {
    templateUrl: _contextPath + '/WEB-INF/views/main.jsp',
    controller: function($rootScope, $scope, $http, $routeParams) { 
    	$scope.findById = function () {
	    	// update only if id chosen
	    	if (!$scope.id) {
	    		return;
	    	}
	    	$scope.errorMessage = "";
	    	
	    	var successCallBack = function(response){
	    		$scope.name = response.data.name;
				$scope.description = response.data.description;
				console.log (response.data);
		    };
			
			$http.get(_contextPath + '/api/place/' +  $routeParams.id).then(successCallBack);
		};
    	 
	    $scope.update = function () {
	    	$scope.id = $routeParams.id;
	    		// update only if id is specified
	        if ($scope.id == undefined) {
	    	    $scope.errorMessage = "Please, select the id of existing place and try again!"
	    	    return;
	    	}	
	    	
	    
    	    var dataObj = {
    		    name: $scope.name,
    	        description: $scope.description,
		    };
    	
    	    var res = $http.put(_contextPath + '/api/place/1/'+$scope.id, dataObj);
    	    res.success(function(data, status, headers, config) {
    			    /*console.log(response);
    			    $scope.name = response.data.name;
    			    $scope.description = response.data.description;*/
    		});
		};
    }
});
    
    


