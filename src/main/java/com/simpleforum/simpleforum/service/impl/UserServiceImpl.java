package com.simpleforum.simpleforum.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.exception.AlreadyExistException;
import com.simpleforum.simpleforum.repository.UserRepository;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyExistException("username already exists");
        }
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistException("email already exists");
        }
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistException("phone number already exists");
        }
        User user = new User();
        user.setID(NanoIdUtils.randomNanoId());
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegistrationDate(new Date());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        return user;
    }

    @Override
    public Boolean login(String username, String email, String phoneNumber, String password) {
        User user = userRepository.findByUsernameOrEmailOrPhoneNumber(username, email, phoneNumber);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        return true;
    }

    @Override
    public User updateUser(User currentUser, String username, String password, String email, String phoneNumber) {
        if (username != null) {
            if (userRepository.existsByUsername(username)) {
                throw new AlreadyExistException("username already exists");
            }
            currentUser.setUsername(username);
        }
        if (password != null) {
            currentUser.setPassword(password);
        }
        if (email != null) {
            if (userRepository.existsByEmail(email)) {
                throw new AlreadyExistException("email already exists");
            }
            currentUser.setEmail(email);
        }
        if (phoneNumber != null) {
            if (userRepository.existsByPhoneNumber(phoneNumber)) {
                throw new AlreadyExistException("phone number already exists");
            }
            currentUser.setPhoneNumber(phoneNumber);
        }
        userRepository.save(currentUser);
        return currentUser;
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
        return userRepository.findByUsername(username);
    }
}
