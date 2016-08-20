package ua.softserve.rv_018.greentourism.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceServiceImpl;

@Controller
public class PlaceController {

	@Autowired
	private PlaceServiceImpl placeService;

	@RequestMapping(value = "/place", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
	public @ResponseBody ResponseEntity<?> findByNameIgnoreCaseContaining(
			@RequestBody String name) {
		Collection<Place> places = placeService
				.findByNameIgnoreCaseContaining(name);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}
}
