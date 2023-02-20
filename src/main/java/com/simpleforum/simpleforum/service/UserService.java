package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.User;

public interface UserService {
    /**
     * Register a new user
     * @param username username
     * @param password password
     * @param email email
     * @param phoneNumber phone number
     * @return the new user
     */
    User register(String username, String password, String email, String phoneNumber);

    /**
     * Login a user
     * @param username username
     * @param password password
     * @return success or not
     */
    Boolean login(String username, String password);
}
