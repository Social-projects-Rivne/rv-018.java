package ua.softserve.rv_018.greentourism.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.softserve.rv_018.greentourism.model.MapBound;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PlaceService;

@RequestMapping(value = "/api")
@Controller
public class PlaceController {

	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlaceService placeService;

	/**
	 * Web service endpoint to fetch all Places entities. The service returns
	 * the collection of Places entities as JSON.
	 * 
	 * @return
	 *
	 * @return A ResponseEntity containing a Collection of Places objects.
	 */
	@RequestMapping(value = "/place", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> getPlaces() {

		logger.info("> getPlaces");

		Collection<Place> places = placeService.findAll();

		logger.info("< getPlaces");

		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@RequestMapping(value = "/currentMapViewportPoints", method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> currentMapViewportPoints(
			@RequestBody MapBound mapBound) {
		logger.info("> get current map viewport points");

		List<Point> currentViewportPoints = new ArrayList<Point>();

		Collection<Place> places = placeService.findAll();
		for (Place place : places) {
			Point point = place.getPoint();
			if (mapBound.contains(point)) {
				currentViewportPoints.add(point);
			}
		}

		logger.info("< get current map viewport points");

		return new ResponseEntity<>(currentViewportPoints, HttpStatus.OK);
	}

	@RequestMapping(value = "/place", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public @ResponseBody ResponseEntity<?> findByNameIgnoreCaseContaining(
			@RequestBody String name) {
		Collection<Place> places = placeService
				.findByNameIgnoreCaseContaining(name);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addplace", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			"application/json" })
	public ResponseEntity<?> createPlace(@RequestBody Place place) {
		logger.info("> createPlace");
		
		Place savedPlace = placeService.create(place);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPlace.getId()).toUri());

		logger.info("< createPlace");

		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}
}
