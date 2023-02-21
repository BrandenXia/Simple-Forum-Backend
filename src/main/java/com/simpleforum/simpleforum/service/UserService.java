package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.dto.UserDTO;
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
     *
     * @param user user
     * @param loginType login type
     * @return success or not
     */
    Boolean login(UserDTO user, String loginType) throws Exception;

    /**
     * Login a user
     * @param username username
     * @param password password
     * @return success or not
     */
    Boolean loginWithUsername(String username, String password);

    /**
     * Login a user
     * @param email email
     * @param password password
     * @return success or not
     */
    Boolean loginWithEmail(String email, String password);

    /**
     * Update a user
     *
     * @param currentUser current user
     * @param updateUser  update user
     * @return success or not
     */
    Boolean updateUser(User currentUser, UserDTO updateUser);

    /**
     * Get a user by username
     * @param token token
     * @return the user or null
     */
    User getCurrentUser(String token);
}
