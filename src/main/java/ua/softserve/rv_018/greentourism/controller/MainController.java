package ua.softserve.rv_018.greentourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainpage(ModelMap model) {
		model.addAttribute("message", "Welcome to the Green tourism service!");
		
		return "main";
	}
}
