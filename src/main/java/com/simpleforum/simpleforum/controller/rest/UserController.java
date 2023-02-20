package com.simpleforum.simpleforum.controller.rest;

import com.auth0.jwt.interfaces.Claim;
import com.simpleforum.simpleforum.dto.UserDTO;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.JwtUtils;
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
    public Map<String, Object> register(@RequestBody UserDTO registerUser) {
        if (registerUser.getUsername() == null || registerUser.getPassword() == null) {
            return Map.of("status", "error", "message", "register failed: invalid username or password");
        }
        User user = userService.register(registerUser.getUsername(), registerUser.getPassword(), registerUser.getEmail(), registerUser.getPhoneNumber());
        if (user == null) {
            return Map.of("status", "error", "message", "register failed: username or email already exists");
        }
        return Map.of("status", "ok", "message", "register success");
    }

    @PostMapping("/login/username")
    public Map<String, Object> loginWithUsername(@RequestBody UserDTO user) {
        if (!userService.loginWithUsername(user.getUsername(), user.getPassword())) {
            return Map.of("status", "error", "message", "invalid username or password");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return Map.of("status", "ok", "message", "loginWithUsername success", "token", token);
    }

    @PostMapping("/login/email")
    public Map<String, Object> loginEmail(@RequestBody UserDTO user) {
        if (!userService.loginWithEmail(user.getEmail(), user.getPassword())) {
            return Map.of("status", "error", "message", "invalid email or password");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("email", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return Map.of("status", "ok", "message", "loginWithUsername success", "token", token);
    }

    @PostMapping("/current")
    public Map<String, Object> current(@RequestHeader("token") String token) {
        Map<String, Claim> payload = JwtUtils.getPayload(token);
        if (payload == null) {
            return Map.of("status", "error", "message", "invalid token");
        }
        String username = payload.get("username").asString();
        return Map.of("status", "ok", "message", "get current user success", "user", username);
    }
}
