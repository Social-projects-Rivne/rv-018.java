package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.softserve.rv_018.greentourism.repository.PasswordResetTokenRepository;
import ua.softserve.rv_018.greentourism.repository.UserRepository;
import ua.softserve.rv_018.greentourism.model.PasswordResetToken;
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
    
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
	public User create(User user) {
		logger.info("> User create");

        validateUserBeforeCreating(user.getUsername());
        User savedUser = userRepository.save(user);

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
        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
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
	public void validateUserBeforeCreating(String username) {
		// OpenShift doesn't work with java 1.8
//		this.userRepository.findByUsername(username).ifPresent(
//                (user) -> {throw new UserAlreadyExistsException(user);});
	}
	
	/**
	 * Exception should be thrown before User creating
	 * 	if User with given name already exists
	 */
	@ResponseStatus(HttpStatus.CONFLICT)
	class UserAlreadyExistsException extends RuntimeException {
	    private static final long serialVersionUID = 1L;

		public UserAlreadyExistsException(User user) {
	        super("User already exists: " + user + ".");
	    }
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	@Override
	 public User changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }
	
	@Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}