package ua.softserve.rv_018.greentourism.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

@Component
public class AppAuthenticationManager implements AuthenticationManager {

	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		User user = userService.findByEmail(email);
		if (user == null) {
			System.out.println("User doesn't exist!");
		}
		String expectedUserPassword = user.getPassword();
		if (authentication.getCredentials().equals(expectedUserPassword)) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
					AUTHORITIES);
		}
		throw new BadCredentialsException("Bad Credentials");
	}
}
