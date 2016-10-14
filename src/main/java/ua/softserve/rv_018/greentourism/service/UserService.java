package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

import ua.softserve.rv_018.greentourism.model.PasswordResetToken;
import ua.softserve.rv_018.greentourism.model.User;

public interface UserService {
	/**
     * Find all User entities.
     * @return A Collection of User objects.
     */
    Collection<User> findAll();

    /**
     * Find a single User entity by primary key identifier.
     * @param id A Long primary key identifier.
     * @return A User or <code>null</code> if none found.
     */
    User findOne(Long id);
    
    /**
     * Find a single User entity by email and password.
     * @param id A Long primary key identifier.
     * @return A User or <code>null</code> if none found.
     */
    User findByEmailAndPassword(String email, String pasword);
    
    /**
     * Find a single User entity by email.
     * @param id A Long primary key identifier.
     * @return A User or <code>null</code> if none found.
     */
    User findByEmail(String email);
    
    /**
     * Find a single User entity by token value.
     * @param token A String token param of user.
     * @return A User or <code>null</code> if none found.
     */
    User findByToken(String token);
    
    /**
     * Persists a User entity in the data store.
     * @param user A User object to be persisted.
     * @return The persisted User entity.
     */
    User create(User user, String domain);

    /**
     * Updates a previously persisted User entity in the data store.
     * @param user A User object to be updated.
     * @return The updated User entity.
     */
    User update(User user);

    /**
     * Removes a previously persisted User entity from the data store.
     * @param id A Long primary key identifier.
     */
    void delete(Long id);

    /**
     * Validate User before creating.
     * @param user.
     * @exception UserAlreadyExistsException if user with given name already exists.
     * @exception InvalidCredentialsException if user data is invalid.
     */
    void validateUserBeforeCreating(User user);
    
    /**
     * Sends Email to User witch message and subject as given in params
     * @param user
     * @param message should be given in html format
     * @param subject
     */
    void sendEmail(User user, String message, String subject);
    
    /**
     * Checks if token is valid and if so updates user isActive to true
     * @param token
     */
    void confirmEmail(String token);
    
    /**
     * Create for user if user want to resset password
     * @param token
     */
    void createPasswordResetTokenForUser(User user, String token);

	PasswordResetToken getPasswordResetToken(String token);

	User changeUserPassword(User user, String password);

	User findUserByToken(String token);

}
