package ua.softserve.rv_018.greentourism.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import ua.softserve.rv_018.greentourism.config.authentication.TokenUtil;
import ua.softserve.rv_018.greentourism.config.authentication.UsernamePasswordAuthenticationErrorHandler;
import ua.softserve.rv_018.greentourism.config.authentication.UsernamePasswordAuthenticationSuccessHandler;

@ComponentScan("ua.softserve.rv_018.greentourism.config")
public class AppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public final static String FILTERED_URL = "/**";
	
	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private AppAuthenticationManager authenticationManager;
	
	@Autowired
	private UsernamePasswordAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private UsernamePasswordAuthenticationErrorHandler failureHandler;

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
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("Error handler: " + getFailureHandler());
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			throw new BadCredentialsException("Credentials are not valid!");
		}

		String authToken = header.substring(6);

		UsernamePasswordAuthenticationToken authRequest = tokenUtil.parseToken(authToken);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
		/*
		 * As this authentication is in HTTP header, after success we need to
		 * continue the request normally and return the response as if the
		 * resource was not secured at all
		 */
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		setAuthenticationSuccessHandler(successHandler);
		setAuthenticationFailureHandler(failureHandler);
	}
	
	
	
}
