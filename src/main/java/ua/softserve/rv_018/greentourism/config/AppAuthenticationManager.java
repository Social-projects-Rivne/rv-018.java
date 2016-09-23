package ua.softserve.rv_018.greentourism.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.config.authentication.UsernamePasswordAuthenticationProvider;

@Component
public class AppAuthenticationManager implements AuthenticationManager {
	
	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return usernamePasswordAuthenticationProvider.authenticate(authentication);
	}

}
