package ua.softserve.rv_018.greentourism.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.softserve.rv_018.greentourism.config.AppAuthenticationProvider;

@Controller
public class LoginController {

	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppAuthenticationProvider appAuthenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			"application/json" })
	public ResponseEntity<?> loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("> loginUser by email: " + email);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		Authentication result = appAuthenticationManager.authenticate(token);
		if (result != null) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(result);

			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

			HttpHeaders headers = new HttpHeaders();
			headers.add("UserId", result.toString());
			return new ResponseEntity<>(headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			"application/json" })
	public ResponseEntity<?> logoutUser(HttpServletRequest request, HttpServletResponse response) {

		logger.info("> logout User ");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			session.invalidate();
			Authentication currentAuthentication = context.getAuthentication();
			if (currentAuthentication != null) {
				new SecurityContextLogoutHandler().logout(request, response, currentAuthentication);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}