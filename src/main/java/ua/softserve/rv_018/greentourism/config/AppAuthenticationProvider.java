package ua.softserve.rv_018.greentourism.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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
			return null;
		}
		String expectedUserPassword = user.getPassword();
		AUTHORITIES.add(new SimpleGrantedAuthority(user.getRole().getName()));
		if (authentication.getCredentials().equals(expectedUserPassword)) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authentication.getName(), authentication.getCredentials(), AUTHORITIES);
			token.setDetails(user.getId());
			return token;
		} else
			return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}
}
