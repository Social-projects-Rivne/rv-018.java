package ua.softserve.rv_018.greentourism.config.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;

@Component
public class TokenUtil {

	/**
	 * Tries to parse specified String as a JWT token. If successful, returns
	 * User object with username, id and role prefilled (extracted from token).
	 * If unsuccessful (token is invalid or not containing all required user
	 * properties), simply returns null.
	 * 
	 * @param token
	 *            the JWT token to parse
	 * @return the User object extracted from specified token or null if a token
	 *         is invalid.
	 */
	public User parseToken(UsernamePasswordAuthenticationToken token) {
		User user = new User();
		user.setEmail((String) token.getPrincipal());
		user.setPassword((String) token.getCredentials());
		return user;
	}

	/**
	 * Generates a JWT token containing username as subject, and userId and role
	 * as additional claims. These properties are taken from the specified User
	 * object. Tokens validity is infinite.
	 * 
	 * @param u
	 *            the user for which the token will be generated
	 * @return the JWT token
	 */
	public UsernamePasswordAuthenticationToken generateToken(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		return token;
	}
}
