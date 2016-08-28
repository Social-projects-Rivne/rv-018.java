'use strict';

angular
		.module('greenApp')
		.component(
				'place',
				{
					templateUrl : 'resources/app/components/place/place.template.html',
					controller : function($scope, $http, $routeParams) {

						$scope.findById = function() {
							// update only if id chosen
							if (!$scope.id) {
								return;
							}
							$scope.errorMessage = "";

							var successCallBack = function(response) {
								// console.log(response);
								$scope.name = response.data.name;
								$scope.description = response.data.description;
								
								$scope.short_description = $scope.description.substr(0, 150);
								
								$scope.location = response.data.location;
								$scope.otherInfo = "facebook.com/ronSmith";
								$scope.feedbacks = "I've been there and I loved it!";
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

								$scope.images = [
										"http://67.media.tumblr.com/c45a87fc2847f98be849856bfcc55adc/tumblr_nstlpj9iz41r9943oo1_1280.jpg",
										"http://65.media.tumblr.com/f9d693b0c810ecf3e75f472a2c9e248c/tumblr_nstlpj9iz41r9943oo3_1280.jpg",
										"http://66.media.tumblr.com/46b7dfc7211278a2e30f362506e7b573/tumblr_nstlpj9iz41r9943oo2_1280.jpg",
										"http://67.media.tumblr.com/074e23d23dff280f91d91874b9845b13/tumblr_nstlpj9iz41r9943oo5_1280.jpg" ];
								var myFunc = function() {
									$('.carousel').carousel({
										dist:1000,
										shift:1000,
										padding:1000,
									});
								}
								setTimeout(myFunc, 1500);
							};

							var failCallback = function(response) {
								// console.log(response);
							};

							$http.get(
									'http://localhost:8080/api/place/place/'
											+ $scope.id).then(successCallBack,
									failCallback);
							
							function changeImage(dir) {
						        var img = document.getElementById("imgClickAndChange");
						        img.src = imgs[imgs.indexOf(img.src) + (dir || 1)] || imgs[dir ? imgs.length - 1 : 0];
						    }

						    document.onkeydown = function(e) {
						        e = e || window.event;
						        if (e.keyCode == '37') {
						            changeImage(-1) //left <- show Prev image
						        } else if (e.keyCode == '39') {
						            // right -> show next image
						            changeImage()
						        }
						    }
						    var status = "less";

						      
						};
						
						$scope.less = true;
						$scope.less_more = function($event)
					    {
							if($scope.less) {
								$(".less").addClass("hidden");
					        	$(".more").removeClass("hidden");
							} else {
								$(".more").addClass("hidden");
					        	$(".less").removeClass("hidden");
							}
							$scope.less = !$scope.less;
							$event.preventDefault();
					    }  

						$scope.id = $routeParams.placeId;
						$scope.findById();

						

					}
				});
