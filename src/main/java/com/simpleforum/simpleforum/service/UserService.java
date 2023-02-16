package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.User;

public interface UserService {
    User createUser(String username, String password, String email, String phoneNumber);
    Boolean deleteUser(String id);
    Boolean updateUser(String id, String username, String password, String email, String phoneNumber);
    User getUserByID(String id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByPhoneNumber(String phoneNumber);
}
