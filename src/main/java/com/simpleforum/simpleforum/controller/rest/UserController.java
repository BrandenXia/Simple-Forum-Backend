package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.dto.UserDTO;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
import com.simpleforum.simpleforum.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseUtils.Response register(@RequestBody UserDTO registerUser) {
        if (registerUser.getUsername() == null || registerUser.getPassword() == null) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("invalid username or password");
        }
        User user = userService.register(registerUser.getUsername(), registerUser.getPassword(), registerUser.getEmail(), registerUser.getPhoneNumber());
        if (user == null) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("username or email already exists");
        }
        return ResponseUtils.createResponse()
                .success()
                .setCode(200)
                .setMessage("register success")
                .setData(user);
    }

    @PostMapping("/login/username")
    public ResponseUtils.Response loginWithUsername(@RequestBody UserDTO user) {
        if (!userService.loginWithUsername(user.getUsername(), user.getPassword())) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("invalid username or password");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return ResponseUtils.createResponse()
                .success()
                .setCode(200)
                .setMessage("loginWithUsername success")
                .setData(Map.of("token", token));
    }

    @PostMapping("/login/email")
    public ResponseUtils.Response loginEmail(@RequestBody UserDTO user) {
        if (!userService.loginWithEmail(user.getEmail(), user.getPassword())) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("invalid email or password");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("email", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return ResponseUtils.createResponse()
                .success()
                .setCode(200)
                .setMessage("loginWithEmail success")
                .setData(Map.of("token", token));
    }

    @GetMapping("/current")
    public ResponseUtils.Response current(@RequestHeader("token") String token) {
        User user = userService.getCurrentUser(token);
        if (user == null) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("invalid token");
        }
        return ResponseUtils.createResponse()
                .success()
                .setCode(200)
                .setMessage("get current user success")
                .setData(Map.of("username", user.getUsername()));
    }
}
