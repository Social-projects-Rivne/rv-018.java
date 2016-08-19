package ua.softserve.rv_018.greentourism.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	private PlaceServiceImpl service;

	@RequestMapping(value = "/place", method = RequestMethod.POST, consumes="text/plain", produces = "application/json")
	public @ResponseBody Place getPlaceByName(@RequestBody String name) {
		return service.findPlaceByName(name);
	}
}
