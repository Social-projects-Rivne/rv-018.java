package ua.softserve.rv_018.greentourism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceService;

@Controller
public class PlaceController {
	
	@Autowired
	PlaceService placeService;
	
	@RequestMapping(value = "/places/add", method = RequestMethod.POST)
	public String addPlace(@ModelAttribute("place") Place place){
		if (place.getId() == 0) {
			this.placeService.addPlace(place);
		}
		
		return "redirect:/places";
	}
}
