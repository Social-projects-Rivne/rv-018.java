package ua.softserve.rv_018.greentourism.config.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

@Component
public class UsernamePasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
        return new User();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
		String enteredPassword = (String) authentication.getCredentials();
		
		User user = userService.findByEmail(email);
		if (user == null) {
			return null;
		}
		String expectedUserPassword = user.getPassword();
		
		if (true || encoder.matches(enteredPassword, expectedUserPassword)) {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					authentication.getName(), authentication.getCredentials());
			return token;
		} else
			return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
}
