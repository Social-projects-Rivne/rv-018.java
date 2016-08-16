'use strict';

angular.
  module('greenApp').
  component('user', {
	  templateUrl: _contextPath + '/resources/app/components/user/user.template.html',
	  controller: function($http, $routeParams) {
		var self = this;
		
		$http.get(_contextPath + '/user/' + $routeParams.userId).then(function(response) {
		  self.user = response.data;	
		});
	  }
  });
  