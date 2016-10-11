package ua.softserve.rv_018.greentourism.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.softserve.rv_018.greentourism.model.User;

/**
 * Exception should be thrown before User creating
 * if User's credentials were invalid
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = 2L;
	
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public InvalidCredentialsException(User user) {
		super("Invalid data of user: " + user + ".");
		
		logger.error("Exception thrown");
	}
}
