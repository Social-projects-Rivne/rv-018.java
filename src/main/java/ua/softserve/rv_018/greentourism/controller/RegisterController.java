package ua.softserve.rv_018.greentourism.controller;
 
import ua.softserve.rv_018.greentourism.model.User;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
 

@Controller
public class RegisterController {

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