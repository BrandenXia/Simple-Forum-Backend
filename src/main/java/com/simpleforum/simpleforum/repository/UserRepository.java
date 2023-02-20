package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository {
    /**
     * Create a new user, return the user if success, return null if fail
     *
     * @param username    username
     * @param password    password
     * @param email       email
     * @param phoneNumber phone number
     * @return User
     */
    @Transactional
    User createUser(String username, String password, String email, String phoneNumber);

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
     * @param user        user
     * @param username    username
     * @param password    password
     * @param email       email
     * @param phoneNumber phone number
     */
    @Transactional
    void updateUser(User user, String username, String password, String email, String phoneNumber);

    /**
     * Get a user by id, return the user if success, return null if fail
     *
     * @param id id
     * @return User
     */
    User getUserByID(String id);

    /**
     * Get a user by username, return the user if success, return null if fail
     *
     * @param username username
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * Get a user by email, return the user if success, return null if fail
     *
     * @param email email
     * @return User
     */
    User getUserByEmail(String email);

    /**
     * Get a user by phone number, return the user if success, return null if fail
     *
     * @param phoneNumber phone number
     * @return User
     */
    User getUserByPhoneNumber(String phoneNumber);
}
