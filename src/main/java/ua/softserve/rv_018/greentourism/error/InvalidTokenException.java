package ua.softserve.rv_018.greentourism.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception should be thrown before User Email
 * verification if given token were invalid
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 3L;
	
	/**
	 * The logger service for logging purpose.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public InvalidTokenException(String token) {
		super("Invalid token:" + token + ".");
		
		logger.error("Exception thrown");
	}
}
