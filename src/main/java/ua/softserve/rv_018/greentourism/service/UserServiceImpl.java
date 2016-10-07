package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.softserve.rv_018.greentourism.repository.UserRepository;
import ua.softserve.rv_018.greentourism.service.UserDataInputValidation;
import ua.softserve.rv_018.greentourism.service.Mailin;
import ua.softserve.rv_018.greentourism.model.User;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class UserServiceImpl implements UserService{
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The Spring Data repository for Role entities.
     */
    @Autowired
    private UserRepository userRepository;
    
	@Override
	public Collection<User> findAll() {
		logger.info("> User findAll");

        Collection<User> users = userRepository.findAll();

        logger.info("< User findAll");
        
        return users;
	}

	@Override
	public User findOne(Long id) {
		logger.info("> User findOne id:{}", id);

        User user = userRepository.findOne(id);

        logger.info("< User findOne id:{}", id);

        return user;
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		logger.info("> User findByEmailAndPassword email:{} , password:{}", email, password);

		User user = userRepository.findByEmailAndPassword(email, password);
		
		logger.info("< User findByEmailAndPassword email:{} , password:{}", email, password);
		
		return user;
	}
	
	@Override
	public User findByEmail(String email) {
		logger.info("> User findByEmail email:{}", email);

		User user = userRepository.findByEmail(email);
		
		logger.info("< User findByEmail email:{}", email);
		
		return user;
	}
	
	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
	public User create(User user, String domain) {
		logger.info("> User create");

        validateUserBeforeCreating(user);
        User savedUser = userRepository.save(user);
        
        String subject = "Email verification";
        
        /*Build verification url*/
        String url = "http://" + domain + "/user/confirmation/" 
                     + String.format("%04d", user.getId()) 
                     + (user.getEmail().hashCode() + user.getUsername().hashCode());
        
        /* Build message to be sent */
        String message = "<p>" + "Hello, " + user.getFirstName() + "!" + "</p>"
                       + "<p>" + "Please confirm your email by clicking on this link:" + "</p>"
                       + "<p>" + url + "</p>";
        
        System.out.println(url);
        
        sendEmail(user, message, subject);

        logger.info("< User create");
        
        return savedUser;

	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public User update(User user) {
		logger.info("> User update id:{}", user.getId());

		// Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        User userToUpdate = findOne(user.getId());
        if (userToUpdate == null) {
            // Cannot update User that hasn't been persisted
            logger.error(
                    "Attempted to update a User, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setSocialAccount(user.getSocialAccount());
        userToUpdate.setUserpic(user.getUserpic());
        userToUpdate.setActive(user.isActive());
        User updatedUser = userRepository.save(userToUpdate);

        logger.info("< User update id:{}", updatedUser.getId());
        
        return updatedUser;
	}

	@Override
	@Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public void delete(Long id) {
		logger.info("> User delete id:{}", id);

        userRepository.delete(id);

        logger.info("< User delete id:{}", id);
	}

	@Override
	public void validateUserBeforeCreating(User user) {
		if (userRepository.findByUsername(user.getUsername()) != null ||
			userRepository.findByEmail(user.getEmail()) != null) {
				throw new UserAlreadyExistsException(user);
		}
		
		if (!UserDataInputValidation.validateEmail(user.getEmail()) ||
			!UserDataInputValidation.validateName(user.getFirstName()) ||
			!UserDataInputValidation.validateName(user.getLastName()) ||
			!UserDataInputValidation.validatePassword(user.getPassword()) ||
			!UserDataInputValidation.validateUsername(user.getUsername())) {
				throw new InvalidCredentialsException(user);
		}
		
	}
	
	@Override
	public void sendEmail(User user, String message, String subject) {
		Mailin http = new Mailin("https://api.sendinblue.com/v2.0","D6fFX3OIqtWU02ms");
		
		Map < String, String > to = new HashMap < String, String > ();
		to.put(user.getEmail(), user.getFirstName());
		
		Map < String, String > headers = new HashMap < String, String > ();
		headers.put("Content-Type", "text/html; charset=iso-8859-1");
		
		
		Map < String, Object > data = new HashMap < String, Object > ();
		data.put("to", to);
		data.put("from", new String [] {"green.tour.inc@gmail.com","GreenTourism"});
		data.put("subject", subject);
		data.put("html", message);
		data.put("headers", headers);
		
		String str = http.send_email(data);
		
		logger.info(str);
	}
	
	@Override
	public void confirmEmail(String token) {
		logger.info("> User confirmEmail by token: {}", token);
		
		User user = userRepository.findOne(Long.parseLong(token.substring(0, 4)));
		
		if (user == null) {
			logger.error(
                    "Attempted to update a User, but the entity does not exist.");
			
			throw new InvalidTokenException(token);
		}
		
		if (user.getEmail().hashCode() + user.getUsername().hashCode() == Integer.parseInt(token.substring(4))) {
			user.setActive(true);
			update(user);
			
			logger.info("< User confirmEmail by token: {}", token);
		} else {
			logger.error(
                    "Attempted to update a User, but the token: {} is invalid.", token);
			
			throw new InvalidTokenException(token);
		}
	}
	
	/**
	 * Exception should be thrown before User creating
	 * if User with given name already exists
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	class UserAlreadyExistsException extends RuntimeException {
	    private static final long serialVersionUID = 1L;

		public UserAlreadyExistsException(User user) {
	        super("User already exists: " + user + ".");
	    }
	}
	
	/**
	 * Exception should be thrown before User creating
	 * if User's credentials were invalid
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	class InvalidCredentialsException extends RuntimeException {
		private static final long serialVersionUID = -1L;
		
		public InvalidCredentialsException(User user) {
			super("Invalid data of user: " + user + ".");
		}
	}
	
	/**
	 * Exception should be thrown before User Email
	 * verification if given token were invalid
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	class InvalidTokenException extends RuntimeException {
		private static final long serialVersionUID = -124314L;
		
		public InvalidTokenException(String token) {
			super("Invalid token:" + token + ".");
		}
	}
}