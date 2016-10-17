package ua.softserve.rv_018.greentourism.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.softserve.rv_018.greentourism.model.Place;

/**
 * Exception should be thrown before Place updating
 * if Place's credentials were invalid
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
	public class InvalidPlaceCredentialsException extends RuntimeException {
		private static final long serialVersionUID = 2L;
		
		/**
		 * The logger service for logging purpose.
		 */
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		public InvalidPlaceCredentialsException(Place place) {
			super("Invalid data of place: " + place + ".");
			
			logger.error("Exception thrown");
	    }
    }
