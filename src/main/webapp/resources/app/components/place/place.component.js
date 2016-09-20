'use strict';

angular
		.module('greenApp')
		.controller('OpenPlaceCtrl')
		.component(
				'place',
				{
					templateUrl : _contextPath +'/resources/app/components/place/place.template.html',
					controller : function($scope, $http, $routeParams, $location, $route) {
						
						$scope.findById = function() {
							// update only if id chosen
							if (!$scope.id) {
								return;
							}
							$scope.errorMessage = "";

							var successCallBack = function(response) {
								
								$scope.name = response.data.name;
								$scope.description = response.data.description;

								$scope.short_description = $scope.description
										.substr(0, 200);

								$scope.location = response.data.location;
								$scope.otherInfo = response.data.user;
								
								$scope.userpicture = response.data.user;
								
								$scope.feedbacks = [
										{
											username : "Elly Swanson",
											date : "20 Oct 2016",
											avatar : "/resources/images/user_icon.png",
											text : "I've been there and I loved it! Proin luctus mi et tincidunt gravida. "
													+ "Quisque vehicula eget risus dapibus bibendum. Phasellus imperdiet urna nec "
													+ "ipsum porttitor, ut mollis metus viverra. Nullam metus sapien, vestibulum "
													+ "venenatis elit at, maximus faucibus risus. Duis scelerisque, augue cursus "
													+ "gravida fermentum, leo orci tempor sapien, id maximus purus metus ut velit. "
													+ "Quisque turpis nisi, feugiat sed erat eget, pellentesque maximus orci. "
													+ "Nulla finibus volutpat ante, id aliquet nisl consequat nec. Donec eget "
													+ "iaculis leo. Morbi imperdiet risus sem, eu faucibus orci gravida eget. Aenean tempor hendrerit tincidunt. Quisque sed pulvinar purus. Quisque convallis lacus cursus, fringilla augue a, consequat urna. Mauris eget iaculis magna."
										},
										{
											username : "Howard Donaldson",
											date : "1 Oct 2016",
											avatar : "/resources/images/user_icon.png",
											text : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer est sapien, faucibus ultrices justo eget, hendrerit vulputate mi. Curabitur eget ex lacinia, facilisis ante nec, posuere magna. Sed at aliquet nisl. Vivamus in erat euismod, ornare erat id, pharetra erat. In hac habitasse platea dictumst. Donec rhoncus eu massa nec convallis. Aenean feugiat ultricies convallis. Suspendisse vel aliquam lacus. In nec semper ligula. Cras imperdiet posuere dapibus. Cras gravida efficitur feugiat. Fusce sodales, velit at egestas venenatis, leo neque dictum sem, et malesuada turpis purus eu purus. Suspendisse sit amet ante id justo tempus mollis blandit quis turpis. Quisque massa ex, gravida a ex id, ornare accumsan nibh."
										} ];
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
								 //console.log($scope.images);
								
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
						
						/*$scope.update = function () {
					    	$scope.id = $routeParams.id;
					    		
				    	    var dataObj = {
				    	    	//id: $routeParams.id,
				    	    	name: $scope.name,
				    	        description: $scope.description,
						    };
				    	    
				    	    var res = $http.put(_contextPath + '/api/place/' + $scope.id, dataObj);
				    	    console.log(dataObj);
				    	    res.success(function(data, status, headers, config) {
				    		});
						};*/
						
						$scope.less_more = function($event) {
							console.log($event);
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
							$('#feedback-modal').openModal();
						}
						
						$scope.show_name_modal = function($event) {
							$('#name-modal').openModal();
						}
						
						$scope.show_description_modal = function($event) {
							$('#description-modal').openModal();
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
						 
						$scope.user_feedback = "";
						$scope.placeopened = false;
						$scope.id = $routeParams.placeId;
						//console.log($scope.id);
						$scope.findById();
					}
				});
