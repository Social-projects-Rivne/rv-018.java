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
	 * Encodes authentication token with base64 algorithm
	 * 
	 * @param token
	 *            Authentication token which will be encoded
	 * @return Encoded String
	 */
	public static String encodeToken(Authentication token) {
		return new String(Base64.getEncoder()
				.encodeToString((token.getName() + ":" + (String) token.getCredentials()).getBytes()));
	}

	/**
	 * Saves encoded authentication token String representation to user in
	 * database.
	 * 
	 * @param authentication
	 *            Authentication token which will be saved
	 */
	public void saveTokenToUser(Authentication authentication) {
		User user = userService.findByEmail(authentication.getName());
		user.setToken(encodeToken(authentication));
		userService.update(user);
	}

	/**
	 * Deletes encoded authentication token String representation from user in
	 * database.
	 * 
	 * @param authentication
	 *            Authentication token which will be deleted
	 */
	public void deleteTokenFromUser(Authentication authentication) {
		User user = userService.findByEmail(authentication.getName());
		user.setToken(null);
		userService.update(user);
	}

	/**
	 * Generates UsernamePasswordAuthenticationToken from header value
	 * 
	 * @param header
	 *            Header String value which will be transformed
	 * 
	 * @return UsernamePasswordAuthenticationToken
	 */
	public static UsernamePasswordAuthenticationToken genarateTokenFromHeader(String header) {
		byte[] decoded = Base64.getDecoder().decode(header.substring(6));

		String usernameAndPasswordString = new String(decoded);

		int dividerIndex = usernameAndPasswordString.indexOf(":");
		String email = usernameAndPasswordString.substring(0, dividerIndex);
		String password = usernameAndPasswordString.substring(dividerIndex + 1);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		return token;
	}

	/**
	 * Generates a Authorization header from token.
	 * 
	 * @param token
	 *            The token which will be transformed
	 * 
	 * @return String token representation
	 */

	public static String generateAuthorizationHeaderFromToken(Authentication token) {
		if ((token.getName() == null) || (token.getCredentials() == null))
			return "No authorization";
		return "Basic " + encodeToken(token);
	}
	
	/**
	 * Find user by header authorization value.
	 * 
	 * @param header
	 *            The header by which User is found
	 * 
	 * @return User object
	 */
	
	public User getUserFromHeader(String header){
		if (header == null) {
			return null;
		} else {
			String encodedToken = header.substring(6);
			return userService.findByToken(encodedToken);
			
		}
	}
	
	

}
