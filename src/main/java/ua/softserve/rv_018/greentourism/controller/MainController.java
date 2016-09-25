package ua.softserve.rv_018.greentourism.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.softserve.rv_018.greentourism.config.authentication.TokenAuthenticationUtil;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Current user: " + SecurityContextHolder.getContext().getAuthentication());
		String authorizationData = TokenAuthenticationUtil.createAuthorizationHeader(SecurityContextHolder.getContext().getAuthentication());
		System.out.println("Authorization data: " + authorizationData);
		response.setHeader("Authorization", authorizationData);
		return "main";
	}
}