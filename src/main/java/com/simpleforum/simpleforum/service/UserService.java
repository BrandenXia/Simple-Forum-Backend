package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.User;

public interface UserService {
    /**
     * Create a new user, return the user if success, return null if fail
     * @param username username
     * @param password password
     * @param email email
     * @param phoneNumber phone number
     * @return User
     */
    User createUser(String username, String password, String email, String phoneNumber);
    /**
     * Delete a user, return true if success, return false if fail
     * @param id id
     * @return Boolean
     */
    Boolean deleteUser(String id);
    /**
     * Update a user, return true if success, return false if fail
     * @param id id
     * @param username username
     * @param password password
     * @param email email
     * @param phoneNumber phone number
     * @return Boolean
     */
    Boolean updateUser(String id, String username, String password, String email, String phoneNumber);
    /**
     * Get a user by id, return the user if success, return null if fail
     * @param id id
     * @return User
     */
    User getUserByID(String id);
    /**
     * Get a user by username, return the user if success, return null if fail
     * @param username username
     * @return User
     */
    User getUserByUsername(String username);
    /**
     * Get a user by email, return the user if success, return null if fail
     * @param email email
     * @return User
     */
    User getUserByEmail(String email);
    /**
     * Get a user by phone number, return the user if success, return null if fail
     * @param phoneNumber phone number
     * @return User
     */
    User getUserByPhoneNumber(String phoneNumber);
}
