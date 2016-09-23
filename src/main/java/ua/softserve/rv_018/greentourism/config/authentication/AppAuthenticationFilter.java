package ua.softserve.rv_018.greentourism.config.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import ua.softserve.rv_018.greentourism.config.AppAuthenticationManager;

public class AppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public final static String FILTERED_URL = "/map/**";

	@Autowired
	private AppAuthenticationManager authenticationManager;
	
	public AppAuthenticationFilter() {
		super(FILTERED_URL);
		setAuthenticationManager(authenticationManager);
	}
	
	
	@Autowired
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			 return null;
		}

		String authToken = header.substring(7);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("","");
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		/*
		 * As this authentication is in HTTP header, after success we need to
		 * continue the request normally and return the response as if the
		 * resource was not secured at all
		 */
		chain.doFilter(request, response);
	}
}
