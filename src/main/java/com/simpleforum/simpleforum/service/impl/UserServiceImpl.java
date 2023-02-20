package com.simpleforum.simpleforum.service.impl;

import com.simpleforum.simpleforum.repository.UserRepository;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String username, String password, String email, String phoneNumber) {
        if (userRepository.getUserByUsername(username) != null) {
            return null;
        } else if (userRepository.getUserByEmail(email) != null) {
            return null;
        } else if (userRepository.getUserByPhoneNumber(phoneNumber) != null) {
            return null;
        }
        return userRepository.createUser(username, password, email, phoneNumber);
    }

    @Override
    public Boolean login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
