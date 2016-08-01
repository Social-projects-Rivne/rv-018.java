package ua.softserve.rv_018.greentourism.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.softserve.rv_018.greentourism.model.User;

@Controller
public class MainController {
	
	@RequestMapping(value={ "/", "/main" }, method=RequestMethod.GET)
	public String welcomepage(ModelMap model) {
		model.addAttribute("message:", "Welcome to the Green tourism service!");
		
		return "main";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good idea to show login screen again.
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user() {
	    return new ModelAndView("user", "command", new User());
	}
	   
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("SpringWeb")User user, 
	   ModelMap model) {
		  model.addAttribute("id", user.getId());
	      model.addAttribute("email", user.getEmail());
	      model.addAttribute("password", user.getPassword());
	      
	      
	      return "result";
	 }
}