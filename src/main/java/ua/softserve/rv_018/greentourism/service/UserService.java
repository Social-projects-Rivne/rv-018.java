package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

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
     * Find a single User entity by primary key identifier.
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
     * Persists a User entity in the data store.
     * @param user A User object to be persisted.
     * @return The persisted User entity.
     */
    User create(User user);

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
     * Validate User name before creating.
     * @param name A String user name.
     * @exception  UserAlreadyExistsException if user with given name already exists
     */
    void validateUserBeforeCreating(String name);
}