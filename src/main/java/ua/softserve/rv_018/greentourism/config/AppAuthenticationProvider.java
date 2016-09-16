package ua.softserve.rv_018.greentourism.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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
public class AppAuthenticationProvider implements AuthenticationProvider {

	private List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
	
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
		AUTHORITIES.add(new SimpleGrantedAuthority(user.getRole().getName()));
		if (authentication.getCredentials().equals(expectedUserPassword)) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
					AUTHORITIES);
		}
		throw new BadCredentialsException("Bad Credentials");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
