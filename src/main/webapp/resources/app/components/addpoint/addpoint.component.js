'use strict';

angular
		.module('greenApp')
		.component(
				'addpoint',
				{
					templateUrl : 'resources/app/components/addpoint/addpoint.template.html',
					controller : function(placesOnMap, mapMarkingTypes, Place, Track, calendarService,
				               currentUser, constants, Restangular, Event, $scope) {
						var ctrl = this;
						var places = [];
						var placeTypeLength;
						var map = placesOnMap.showMap();
						var activePlacesTypes = [];

						ctrl.user = currentUser;

						// -----START ADD Place-----
						ctrl.newPlace = angular.copy(constants.emptyPlaceModel);
						ctrl.newPlaceType = '';
						ctrl.formNewPlaceSubmitted = false;
						ctrl.addPlaceMenuIsOpen = false;

						ctrl.toggleAddPlaceMenu = function(form) {
							if (ctrl.addPlaceMenuIsOpen) {
								placesOnMap.closeAddPlaceMenu();
								ctrl.addPlaceMenuIsOpen = false;
								ctrl.resetAddPlaceForm(form);
							} else {
								placesOnMap.openAddPlaceMenu();
								ctrl.addPlaceMenuIsOpen = true;
							}
						};

						ctrl.createNewPlace = function(form) {
							var addPlaceForm = angular
									.element('form[name="placeMaker"]');
							var checkActiveType;
							var newPlaces = [];
							ctrl.coordsIsDefined = placesOnMap.coordsIsDefined;
							ctrl.formNewPlaceSubmitted = true;
							if (addPlaceForm.hasClass('ng-valid')
									&& placesOnMap.coords) {
								ctrl.newPlace.type = ctrl.newPlaceType;
								ctrl.newPlace.location.coordinates = placesOnMap.coords;
								newPlaces.push(ctrl.newPlace);
								Restangular
										.oneUrl(
												'location',
												'https://nominatim.openstreetmap.org/reverse?format=json&lat='
														+ placesOnMap.coords[1]
														+ '&lon='
														+ placesOnMap.coords[0]
														+ '&addressdetails=0&zoom=10')
										.get()
										.then(
												function(result) {
													ctrl.newPlace.address = result.display_name;
													Place
															.post(ctrl.newPlace)
															.then(
																	function() {
																		checkActiveType = angular
																				.element('.'
																						+ ctrl.newPlace.type
																						+ ' span');
																		if (checkActiveType
																				.hasClass(constants.checkedSpanClass)) {
																			placesOnMap
																					.showPlaces(newPlaces);
																		} else {
																			ctrl
																					.checkType(ctrl.newPlace.type);
																		}
																		ctrl
																				.resetAddPlaceForm(form);
																		ctrl
																				.toggleAddPlaceMenu(form);
																	});
												});
							}
						};

						ctrl.resetAddPlaceForm = function(form) {
							var newPlaceLongitude = angular
									.element('#longitude');
							var newPlaceLatitude = angular.element('#latitude');
							if (form) {
								ctrl.newPlace = angular
										.copy(constants.emptyPlaceModel);
								ctrl.newPlaceType = '';
								form.$setPristine();
								form.$setUntouched();
								ctrl.formNewPlaceSubmitted = false;
								newPlaceLongitude.text('');
								newPlaceLatitude.text('');
								placesOnMap.coords = [];
								placesOnMap.coordsIsDefined = false;
								placesOnMap.removeNewMarker();
							}
						};
						// -----END ADD Place-----

						// ---START---- Icons for default settings on places and Tracks
						ctrl.defaultObject = function(objectType) {
							var objectIcon = angular.element('.' + objectType);
							if (objectIcon.hasClass(constants.checkedClass)) {
								objectIcon.removeClass(constants.checkedClass);
								if (objectType === 'placesIcon') {
									ctrl.checkAllPlaces(objectIcon);
								}
								if (objectType === 'eventsIcon') {
									ctrl.checkAllEvents('game');
								}
								if (objectType === 'tracksIcon') {
									ctrl.checkAllTracks(objectIcon);
								}
							} else {
								objectIcon.addClass(constants.checkedClass);
								if (objectType === 'placesIcon') {
									ctrl.checkType(constants.placesOnLoad);
								}
								if (objectType === 'eventsIcon') {
									ctrl.checkAllEvents();
								}
								if (objectType === 'tracksIcon') {
									ctrl.checkAllTracks();
								}
							}
						};
						// ---END---- Icons for default settings on places and Tracks

						// ---START--- Places
						ctrl.placesType = mapMarkingTypes.places;
						placeTypeLength = Object.keys(ctrl.placesType).length;
						placesOnMap.removePlaces();
						placesOnMap.initGroupsOfPlaces(ctrl.placesType);

						var placeRequest = function(placesForLoad) {
							var arrayToShow = [];
							var bounds = map.getBounds();
							var zoom = map.getZoom();
							if (placesForLoad)
								arrayToShow.push(placesForLoad);
							else
								arrayToShow = activePlacesTypes;

							var request = {
								type : arrayToShow,
								locationNE : [ bounds._northEast.lng,
										bounds._northEast.lat ],
								locationSW : [ bounds._southWest.lng,
										bounds._southWest.lat ],
								sort : '-rate'
							};

							switch (zoom) {
							case 5:
								request.limit = 200;
								break;
							case 4:
								request.limit = 100;
								break;
							case 3:
								request.limit = 50;
								break;
							case 2:
								request.limit = 25;
								break;
							default:
								delete request.sort;
								break;
							}

							return request;
						};

						ctrl.places = places;

						// Don't hide dropdown if clicked
						angular.element('.dropdownStop').on({
							click : function(e) {
								e.stopPropagation();
							}
						});
					}
				});