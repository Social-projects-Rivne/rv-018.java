'use strict';

angular
		.module('greenApp')
		.component(
				'place',
				{
					templateUrl : _contextPath +'/resources/app/components/place/place.template.html',
					bindings:{isAdmin:"<"},
					controller : function($scope, $http, $routeParams, $route, $rootScope, $location, $localStorage) {
			    		
						if ($localStorage.message == "logout") {
							$http({
								method: 'GET',
								url: _contextPath + "/user/current",
								headers: { 'Authorization': $localStorage.authorization }
							}).then(function(response){ 
								$scope.current_user = response.data;
								console.log(response);
							})
					    }
						
						$scope.findById = function() {
							// update only if id is chosen
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
								$scope.mypoint = response.data.point;
								
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
									
									
									$scope.masonry = $('.grid').masonry({
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
							};

							var failCallback = function(response) {
								// console.log(response);
							};

							$http.get(_contextPath + '/api/place/' + $scope.id).then(successCallBack,failCallback);
							
							function changeImage(dir) {
								var img = document
										.getElementById("imgClickAndChange");
								img.src = imgs[imgs.indexOf(img.src)
										+ (dir || 1)]
										|| imgs[dir ? imgs.length - 1 : 0];
							}

							document.onkeydown = function(e) {
								e = e || window.event;
								if (e.keyCode == '37') {
									changeImage(-1) // left <- show Prev image
								} else if (e.keyCode == '39') {
									// right -> show next image
									changeImage()
								}
							} 
						};
						
						$scope.update_name = function () {
							// update only if id is chosen
							if (!$routeParams.placeId) {
								return;
							}
							if (!$scope.name) {
								   return;
							}
							
							$scope.id = $routeParams.placeId;
							
							if ($localStorage.message == 'logout') {
								   
							    $http({
							    	method: 'PUT',
							    	url: _contextPath + "/api/place/" + $scope.id, 
							    	headers: { 'Authorization': $localStorage.authorization },
							    	data: {
							    		id: $routeParams.placeId,
							    		name: $scope.name
							        }
							    }).then(function(response) {
							    	Materialize.toast('Name successfully updated!', 2000);
								      $scope.submissionSuccess = true;
								      setTimeout(function() {
								        $scope.$apply(function() {
								          $scope.submissionSuccess = false;
								        });
								      }, 50);
							    }, function(error) {
							    	Materialize.toast('Something wrong. Please try again!', 1000);
							    });
							       }
						};
							
						$scope.update_description = function () {
							if (!$routeParams.placeId) {
								return;
							}
							if (!$scope.name) {
								   return;
							}
								$scope.id = $routeParams.placeId;
							  
							   if ($localStorage.message == 'logout') {
								   
							    $http({
							    	method: 'PUT',
							    	url: _contextPath + "/api/place/" + $scope.id,
							    	headers: { 'Authorization': $localStorage.authorization },
							    	data: {
							    		id: $routeParams.placeId,
					    	    		description: $scope.description
							    	}
							    }).then(function(response) {
							    	Materialize.toast('Description successfully added!', 2000);
								      $scope.submissionSuccess = true;
								      setTimeout(function() {
								        $scope.$apply(function() {
								          $scope.submissionSuccess = false;
								        });
								      }, 50);
							    }, function(error) {
							    	Materialize.toast('Something wrong. Please try again!', 1000);
							    });
							       }
						};
						
						$scope.less_more = function($event) {
							
							var elem = $($event.currentTarget).parents('.card')[0];
							var less = $(elem).attr('less') === "true";

							$(elem).attr('less', String(!less));

							if (less) {
								$($event.currentTarget).text("Show less");
							} else {
								$($event.currentTarget).text("Show more");
							}
                            $scope.less = less;
                            setTimeout(function() {
                            	$scope.masonry.masonry();
                            }, 0);
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
							
							$event.preventDefault();
							$location.url("/map/place/" + $scope.mypoint.id);
						}
						 
						$scope.placeopened = true;
						$scope.id = $routeParams.placeId;
						console.log($scope.id);
						$scope.findById();
					}
				});
