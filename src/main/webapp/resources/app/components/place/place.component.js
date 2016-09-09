'use strict';

angular
		.module('greenApp')
		.controller('OpenPlaceCtrl')
		.component(
				'place',
				{
					templateUrl : '/resources/app/components/place/place.template.html',
					controller : function($scope, $http, $routeParams, $location) {
						
						/*$scope.$location = $location;*/
						
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

								$scope.short_description = $scope.description
										.substr(0, 200);

								$scope.location = response.data.location;
								$scope.otherInfo = "facebook.com/ronSmith";
								$scope.feedbacks = [
										{
											username : "Elly Dickinson",
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

								$scope.images = [
										"http://shtukoviny.ru/lg/fort/02.jpg",
										"http://67.media.tumblr.com/c45a87fc2847f98be849856bfcc55adc/tumblr_nstlpj9iz41r9943oo1_1280.jpg",
										"http://65.media.tumblr.com/f9d693b0c810ecf3e75f472a2c9e248c/tumblr_nstlpj9iz41r9943oo3_1280.jpg",
										"http://66.media.tumblr.com/46b7dfc7211278a2e30f362506e7b573/tumblr_nstlpj9iz41r9943oo2_1280.jpg",
										"http://67.media.tumblr.com/074e23d23dff280f91d91874b9845b13/tumblr_nstlpj9iz41r9943oo5_1280.jpg" ];
								var myFunc = function() {
									$('.carousel.carousel-slider').carousel({
										fullWidth : true
									/*
									 * dist:0, shift:0, padding:100,
									 */
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

								var mymap = L
										.map('mapid')
										.setView(
												[
														response.data.point.latitude,
														response.data.point.longitude ],
												15);
								L
										.tileLayer(
												'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw',
												{
													maxZoom : 18,
													attribution : 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, '
															+ '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, '
															+ 'Imagery � <a href="http://mapbox.com">Mapbox</a>',
													id : 'mapbox.streets'
												}).addTo(mymap);

								var marker = new L.Marker({
									lat : response.data.point.latitude,
									lng : response.data.point.longitude,
								}, {
									draggable : false
								});
								mymap.addLayer(marker);

								var mapHeight = $(window).height()
										- $('header').height()
										- $('#tabsRow').height() - 20;
								$('.map-container').css('height',
										mapHeight + 'px');
								$('.place-container').css('top', '0px');
								marker
										.on(
												'click',
												function() {
													$scope
															.$apply(function() {
																$scope.placeopened = true;
															});
													var width = $(
															'.place-container')
															.width();
													var centerX = ($(window)
															.width() - width) / 4;
													$('.place-container').css(
															'right',
															-2 * width + 'px');
													$('.place-container')
															.animate(
																	{
																		right : centerX,
																	}, 1500,
																	'easeOutQuint');
													myFunc();
												});

								// setTimeout(myFunc, 1500);
								// $('.grid').isotope({ layoutMode:
								// 'fitColumns',
								// itemSelector: '.grid-item', percentPosition:
								// true});
							};

							var failCallback = function(response) {
								// console.log(response);
							};

							$http.get(_contextPath + '/api/place/' + $scope.id).then(successCallBack,failCallback);

							/*
							 * function changeImage(dir) { var img = document
							 * .getElementById("imgClickAndChange"); img.src =
							 * $scope.images[$scope.images.indexOf(img.src) +
							 * (dir || 1)] || $scope.images[dir ?
							 * $scope.images.length - 1 : 0]; }
							 * 
							 * document.onkeydown = function(e) { e = e ||
							 * window.event; if (e.keyCode == '37') {
							 * changeImage(-1) // left <- show Prev image } else
							 * if (e.keyCode == '39') { // right -> show next
							 * image changeImage(); } }
							 */
							/*
							 * $(document).ready(function() {
							 * $('.leaflet-marker-icon').click(function() { /*
							 * Act on the event *
							 * $('.close-place').css('visibility': 'visible');
							 * 
							 * });
							 * 
							 * $('.close-place').click(function() { /* Act on
							 * the event * $(this).css('visibility': 'hidden');
							 * 
							 * }); });
							 */
						};

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
						  // Initialize collapsible (uncomment the line below if you use the dropdown variation)
						  //$('.collapsible').collapsible();
						        

						/*$scope.close-place-button = function($event) {
							if($scope.placeopened = false) {
								return false;
					    };*/
						
						/*$scope.close_place = function($event) {
							if ($scope.placeopened) {
								$(".closeIcon").removeClass("hide-it");
							} else {
								$(".closeIcon").addClass("hide-it");
							}
							$scope.placeopened = !$scope.placeopened;
							$event.preventDefault();
						};*/

						$scope.user_feedback = "";
						$scope.placeopened = false;
						$scope.id = $routeParams.placeId;
						$scope.findById();
					}
				});
