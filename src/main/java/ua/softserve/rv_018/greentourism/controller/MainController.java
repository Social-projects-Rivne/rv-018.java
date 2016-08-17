package ua.softserve.rv_018.greentourism.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value={ "/", "/main" }, method=RequestMethod.GET)
	public String welcomepage(ModelMap model) {
		model.addAttribute("message", "Welcome to the Green tourism service!");
		model.addAttribute("user", getAuthenticatedUserName());
		
		return "main";
	}
	
	@RequestMapping(value="/profile/{id}", method=RequestMethod.GET)
	public String openProfile(ModelMap model) {
		model.addAttribute("user", getAuthenticatedUserName());
		
		return "userProfile";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		model.setViewName("login");

		return model;
	}
	
	@RequestMapping(value="/accessDenied", method=RequestMethod.GET)
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		
		if (user != null) {
			model.addObject("errorMessage", user.getName() + " you have no rights to this page!");
		} else {
			model.addObject("errorMessage", "You have no rights to this page!");
		}
		
		model.setViewName("accessDenied");
		
		return model;
	}
	
	private String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return "not authenticated";
		}
		
		Object authenticatedUser = authentication.getPrincipal();
		if (authenticatedUser instanceof String) {
			System.out.println(authenticatedUser);
			return (String) authenticatedUser;
		} else {
			System.out.println(((User) authenticatedUser).getUsername());
			return ((User) authenticatedUser).getUsername();
		}
	}
}