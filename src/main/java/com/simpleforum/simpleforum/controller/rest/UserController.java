package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.dto.ResponseDTO;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.exception.RequestBodyException;
import com.simpleforum.simpleforum.exception.TokenException;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private String getToken(@RequestBody User user) {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        return JwtUtils.getToken(payload);
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody User registerUser) {
        if (registerUser.getUsername() == null || registerUser.getPassword() == null) {
            throw new RequestBodyException("username and password cannot be null");
        }
        User user = userService.register(registerUser.getUsername(), registerUser.getPassword(), registerUser.getEmail(), registerUser.getPhoneNumber());
        return ResponseDTO.builder()
                .status(200)
                .data(Map.of("token", getToken(user)))
                .build();
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody User user) {
        Boolean result = userService.login(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getPassword());
        if (!result) {
            return ResponseDTO.builder()
                    .status(400)
                    .error("invalid username, email, phone number, or password")
                    .build();
        }
        return ResponseDTO.builder()
                .status(200)
                .data(Map.of("token", getToken(user)))
                .build();
    }

    @PostMapping("/update")
    public ResponseDTO update(@RequestHeader("token") String token, @RequestBody User user) {
        User currentUser = userService.getCurrentUser(token);
        if (currentUser == null) {
            throw new TokenException("invalid token");
        }
        if (!user.getPassword().equals(currentUser.getPassword())) {
            return ResponseDTO.builder()
                    .status(400)
                    .error("invalid password")
                    .build();
        }
        if (user.getUsername().equals(currentUser.getUsername())) {
            throw new RequestBodyException("new username cannot be the same as the old one");
        }
        if (user.getEmail().equals(currentUser.getEmail())) {
            throw new RequestBodyException("new email cannot be the same as the old one");
        }
        if (user.getNewPassword().equals(currentUser.getPassword())) {
            throw new RequestBodyException("new password cannot be the same as the old one");
        }
        if (user.getPhoneNumber().equals(currentUser.getPhoneNumber())) {
            throw new RequestBodyException("new phone number cannot be the same as the old one");
        }
        User updateUser = userService.updateUser(currentUser, user.getUsername(), user.getNewPassword(), user.getEmail(), user.getPhoneNumber());
        return ResponseDTO.builder()
                .status(200)
                .data(Map.of("token", getToken(updateUser)))
                .build();
    }

    @GetMapping("/current")
    public ResponseDTO current(@RequestHeader("token") String token) {
        User user = userService.getCurrentUser(token);
        if (user == null) {
            throw new TokenException("invalid token");
        }
    return ResponseDTO.builder()
                .status(200)
                .data(Map.of("user", user))
                .build();
    }
}
