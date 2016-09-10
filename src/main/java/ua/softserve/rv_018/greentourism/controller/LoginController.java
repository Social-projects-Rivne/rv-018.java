package ua.softserve.rv_018.greentourism.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        logger.info("> getUserByEmailAndPassword email:{} , password:{} ", email , password);

        User user = userService.findByEmailAndPassword(email, password);
        
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getUserByEmailAndPassword email:{} , password:{} ", email , password);
        
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	
}
