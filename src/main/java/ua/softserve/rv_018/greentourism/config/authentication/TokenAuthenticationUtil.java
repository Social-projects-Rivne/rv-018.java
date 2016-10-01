package ua.softserve.rv_018.greentourism.config.authentication;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

@Component
public class TokenAuthenticationUtil {

	@Autowired
	private UserService userService;
	
	/**
	 *
	 */
	public static String encodeToken(Authentication token) {
		return new String(Base64.getEncoder()
				.encodeToString((token.getName() + ":" + (String) token.getCredentials()).getBytes()));
	}
	
	/**
	 * 
	 */
	public void saveTokenToUser(Authentication authentication) {
		User user = userService.findByEmail(authentication.getName());
		user.setToken(encodeToken(authentication));
		userService.update(user);
	}
	
	/**
	 * 
	 */
	public void deleteTokenFromUser(Authentication authentication) {
		User user = userService.findByEmail(authentication.getName());
		user.setToken(null);
		userService.update(user);
	}

	/**
	 * 
	 */
	public static UsernamePasswordAuthenticationToken genarateTokenFromData(String data) {
		byte[] decoded = Base64.getDecoder().decode(data.substring(6));

		String usernameAndPasswordString = new String(decoded);

		int dividerIndex = usernameAndPasswordString.indexOf(":");
		String email = usernameAndPasswordString.substring(0, dividerIndex);
		String password = usernameAndPasswordString.substring(dividerIndex + 1);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		return token;
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

	public static String generateAuthorizationHeaderFromToken(Authentication token) {
		if ((token.getName() == null) || (token.getCredentials() == null))
			return "No authorization";
		return "Basic " + encodeToken(token);
	}

}
