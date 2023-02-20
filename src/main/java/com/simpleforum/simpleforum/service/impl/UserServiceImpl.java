package com.simpleforum.simpleforum.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.repository.UserRepository;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        } else {
            return userRepository.createUser(username, password, email, phoneNumber);
        }
    }

    @Override
    public Boolean loginWithUsername(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public Boolean loginWithEmail(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public User getCurrentUser(String token) {
        Map<String, Claim> payload = JwtUtils.getPayload(token);
        if (payload == null) {
            return null;
        }
        String username = payload.get("username").asString();
        if (username == null) {
            return null;
        }
        return userRepository.getUserByUsername(username);
    }
}
