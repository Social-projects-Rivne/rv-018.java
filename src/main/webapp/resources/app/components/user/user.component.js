'use strict';

angular.
  module('greenApp').
  component('user', {
	  templateUrl: 'resources/app/components/user/user.template.html',
	  controller: function($http, $routeParams) {
		var self = this;
		
		$http.get('http://localhost:8080/GreenTourism/user/' + $routeParams.userId).then(function(response) {
		  self.user = response.data;	
		});
	  }
  });
  