package ua.softserve.rv_018.greentourism.config.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationErrorHandler implements AuthenticationFailureHandler {

	@Autowired
	private UsernamePasswordAuthenticationEntryPoint usernamePasswordAuthenticationEntryPoint;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			usernamePasswordAuthenticationEntryPoint.commence(request, response, exception);
	}

}
