package ua.softserve.rv_018.greentourism.controller;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceService;

@Controller
public class PlaceController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PlaceService placeService;

	@RequestMapping(value = "/place", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
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
