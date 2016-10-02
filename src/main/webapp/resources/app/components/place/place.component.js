'use strict';

angular
		.module('greenApp')
		.component(
				'place',
				{
					templateUrl : _contextPath +'/resources/app/components/place/place.template.html',
					controller : function($scope, $http, $routeParams, $route, $rootScope) {
			    		
						$scope.findById = function() {
							// update only if id chosen
							if (!$scope.id) {
								return;
							}
							$scope.errorMessage = "";

							var successCallBack = function(response) {
								console.log(response);
								$scope.name = response.data.name;
								$scope.description = response.data.description;
								$scope.short_description = $scope.description
										.substr(0, 200);

								$scope.location = response.data.location;
								$scope.otherInfo = response.data.user;
								$scope.userpicture = response.data.user;
								
								$scope.feedbacks = response.data.comments;
								
								$http
										.get(
												'http://nominatim.openstreetmap.org/reverse?format=json&lat='
														+ response.data.point.latitude
														+ '&lon='
														+ response.data.point.longitude
														+ '&zoom=18&addressdetails=1&accept-language=ua')
										.then(
												function(response) {
													$scope.region = response.data.address.state;
												}, function(response) {
													// console.log(response);
												});

								$scope.images = response.data.attachments;
								
								var myFunc = function() {
									$('.carousel.carousel-slider').carousel({
										fullWidth : true
									});

									console.log('slider loaded!');
									$('.grid').masonry({
										itemSelector : '.grid-item',
										gutter : 25,
									});
									console.log('masonry applied');
								}

								$('.before-arrow').click(function() {
									/* Act on the event */
									$('.carousel-slider').carousel('prev');
								});
								$('.next-arrow').click(function(event) {
									/* Act on the event */
									$('.carousel-slider').carousel('next');
								});

								 setTimeout(myFunc, 1500);
								 $('.grid').isotope({ layoutMode:
								 'fitColumns',
								 itemSelector: '.grid-item', percentPosition:
								 true});
							};

							var failCallback = function(response) {
								// console.log(response);
							};

							$http.get(_contextPath + '/api/place/' + $scope.id).then(successCallBack,failCallback);
						};
						
						$scope.update_name = function () {
				    		
							$scope.id = $routeParams.placeId;
							
				    	    var dataObj = {
				    	    		id: $routeParams.placeId,
				    	    		name: $scope.name
						    };
				    	    
				    	    var successCallback = function(response){
							      $scope.submissionSuccess = true;
							      setTimeout(function() {
							        $scope.$apply(function() {
							          $scope.submissionSuccess = false;
							        });
							      }, 5000);
							    };

						    var errorCallback = function(response){
						      console.log(response);
						      $scope.submissionError = true;
						      $scope.submissionSuccess = false;
						      setTimeout(function() {
						        $scope.$apply(function() {
						          $scope.submissionError = false;
						        });
						      }, 5000);
						    };
				    	    
				    	    $http.put(_contextPath + '/api/place/' + $scope.id, dataObj).then(successCallback, errorCallback);
				    	    console.log(dataObj);
				    	    
						};
						
						$scope.update_description = function () {
				    		
							/*console.log("update");*/
							$scope.id = $routeParams.placeId;
					    	
				    	    var dataObj = {
				    	    		id: $routeParams.placeId,
				    	    		description: $scope.description
						    };
				    	    
				    	    var successCallback = function(response){
							      $scope.submissionSuccess = true;
							      setTimeout(function() {
							        $scope.$apply(function() {
							          $scope.submissionSuccess = false;
							        });
							      }, 5000);
							    };

						    var errorCallback = function(response){
						      console.log(response);
						      $scope.submissionError = true;
						      $scope.submissionSuccess = false;
						      setTimeout(function() {
						        $scope.$apply(function() {
						          $scope.submissionError = false;
						        });
						      }, 5000);
						    };
						    
				    	    $http.put(_contextPath + '/api/place/' + $scope.id, dataObj).then(successCallback, errorCallback);
				    	    console.log(dataObj);
				    	    
						 };
						
						$scope.less_more = function($event) {
							//console.log($event);
							var elem = $($event.currentTarget).parents('.card')[0];
							var less = $(elem).attr('less') === "true";

							$(elem).attr('less', String(!less));

							if (less) {
								$($event.currentTarget).text("Show less");
							} else {
								$($event.currentTarget).text("Show more");
							}

							$event.preventDefault();
						}

						$scope.show_modal = function($event) {
							$('#feedback-modal').appendTo($("body")).openModal();
						}
						
						$scope.show_name_modal = function($event) {
							$('#name-modal').appendTo($("body")).openModal();
						}
						
						$scope.show_description_modal = function($event) {
							$('#description-modal').appendTo($("body")).openModal();
						}

						$scope.close_place = function($event) {
							$scope.placeopened = false;
							console.log($scope.placeopened);
							$event.preventDefault();
						}
						
						 $('.button-collapse').sideNav({
						      menuWidth: "100%", // Default is 240
						      edge: 'right', // Choose the horizontal origin
						      closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
						    }
						  );
						 
						$scope.placeopened = false;
						$scope.id = $routeParams.placeId;
						console.log($scope.id);
						$scope.findById();
					}
				});
