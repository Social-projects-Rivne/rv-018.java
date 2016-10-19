package ua.softserve.rv_018.greentourism.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.softserve.rv_018.greentourism.model.User;

/**
 * Exception should be thrown before User creating
 * if User with given name already exists
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public UserAlreadyExistsException(User user) {
		super("User already exists: " + user + ".");
		
		logger.error("Exception thrown");
	}
}
