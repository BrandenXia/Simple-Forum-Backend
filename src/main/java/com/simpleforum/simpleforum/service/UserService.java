package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.User;

public interface UserService {
    User register(String username, String password, String email, String phoneNumber);

    Boolean login(String username, String password);
}
