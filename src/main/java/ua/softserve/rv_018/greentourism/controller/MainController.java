package ua.softserve.rv_018.greentourism.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomepage() {
		System.out.println("Current user: " + SecurityContextHolder.getContext().getAuthentication());
		return "main";
	}
}