package com.simpleforum.simpleforum.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.simpleforum.simpleforum.dto.UserDTO;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.repository.UserRepository;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String password, String email, String phoneNumber) {
        if (userRepository.isUserExistsByUsername(username)) {
            return null;
        } else if (userRepository.isUserExistsByEmail(email)) {
            return null;
        } else if (userRepository.isUserExistsByPhoneNumber(phoneNumber)) {
            return null;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            userRepository.createUser(user);
            return user;
        }
    }

    @Override
    public Boolean login(UserDTO user, String loginType) throws Exception {
        String loginMethod = "loginWith" + loginType.substring(0, 1).toUpperCase() + loginType.substring(1);
        String getMethod = "get" + loginType.substring(0, 1).toUpperCase() + loginType.substring(1);
        String userInfo = (String) user.getClass().getMethod(getMethod).invoke(user);
        return (Boolean) getClass().getMethod(loginMethod, String.class, String.class).invoke(this, userInfo, user.getPassword());
    }

    @Override
    public Boolean loginWithUsername(String username, String password) {
        try {
            User user = userRepository.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
                userRepository.updateUser(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean loginWithEmail(String email, String password) {
        try {
            User user = userRepository.getUserByEmail(email);
            if (user.getPassword().equals(password)) {
                user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
                userRepository.updateUser(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
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
        if (userRepository.isUserExistsByUsername(username)) {
            return userRepository.getUserByUsername(username);
        } else {
            return null;
        }
    }
}
