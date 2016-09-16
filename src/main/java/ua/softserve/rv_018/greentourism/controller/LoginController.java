package ua.softserve.rv_018.greentourism.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.config.AppAuthenticationManager;

@Controller
public class LoginController {

	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppAuthenticationManager appAuthenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public ResponseEntity<?> loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request) {

		Authentication queryRequest = new UsernamePasswordAuthenticationToken(email, password);

		Authentication result = appAuthenticationManager.authenticate(queryRequest);
		SecurityContextHolder.getContext().setAuthentication(result);

		System.out.println(SecurityContextHolder.getContext().getAuthentication());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			"application/json" })
	public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {

		Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
		if (currentAuthentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, currentAuthentication);
		}

		System.out.println(SecurityContextHolder.getContext().getAuthentication());

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
