package ua.softserve.rv_018.greentourism.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import ua.softserve.rv_018.greentourism.config.authentication.TokenAuthenticationUtil;
import ua.softserve.rv_018.greentourism.config.authentication.UsernamePasswordAuthenticationSuccessHandler;

@ComponentScan("ua.softserve.rv_018.greentourism.config")
public class AppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String FILTERED_URL = "/**";

	@Autowired
	private AppAuthenticationManager authenticationManager;

	@Autowired
	private UsernamePasswordAuthenticationSuccessHandler successHandler;

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
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Basic ")) {
			return new UsernamePasswordAuthenticationToken(null, null);
		}

		String authToken = header.substring(6);

		UsernamePasswordAuthenticationToken authRequest = TokenAuthenticationUtil.genarateTokenFromHeader(authToken);
		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		/*
		 * As this authentication is in HTTP header, after success we need to
		 * continue the request normally and return the response as if the
		 * resource was not secured at all
		 */
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		setAuthenticationSuccessHandler(successHandler);
	}

}
