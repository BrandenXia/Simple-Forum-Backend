package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.exception.DAOException;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {
    /**
     * Create a new user, return the user if success, return null if fail
     *
     * @param user user
     */
    @Transactional
    void createUser(User user);

    /**
     * Delete a user, return true if success, return false if fail
     *
     * @param user user
     */
    @Transactional
    void deleteUser(User user);

    /**
     * Update a user, return true if success, return false if fail
     *
     * @param user user
     */
    @Transactional
    void updateUser(User user);

    /**
     * Get a user by id, return the user if success, return null if fail
     *
     * @param id id
     * @throws DAOException DAOException
     * @return User
     */
    User getUserByID(String id) throws DAOException;

    /**
     * Get a user by username, return the user if success, return null if fail
     *
     * @param username username
     * @throws DAOException DAOException
     * @return User
     */
    User getUserByUsername(String username) throws DAOException;

    /**
     * Get a user by email, return the user if success, return null if fail
     *
     * @param email email
     * @throws DAOException DAOException
     * @return User
     */
    User getUserByEmail(String email) throws DAOException;

    /**
     * Get a user by phone number, return the user if success, return null if fail
     *
     * @param phoneNumber phone number
     * @throws DAOException DAOException
     * @return User
     */
    User getUserByPhoneNumber(String phoneNumber) throws DAOException;

    /**
     * Check if a user exists by id, return true if exists, return false if not exists
     *
     * @param id id
     * @return boolean
     */
    boolean isUserExistsByID(String id);

    /**
     * Check if a user exists by username, return true if exists, return false if not exists
     *
     * @param username username
     * @return boolean
     */
    boolean isUserExistsByUsername(String username);

    /**
     * Check if a user exists by email, return true if exists, return false if not exists
     *
     * @param email email
     * @return boolean
     */
    boolean isUserExistsByEmail(String email);

    /**
     * Check if a user exists by phone number, return true if exists, return false if not exists
     *
     * @param phoneNumber phone number
     * @return boolean
     */
    boolean isUserExistsByPhoneNumber(String phoneNumber);
}
