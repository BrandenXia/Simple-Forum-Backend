package com.simpleforum.simpleforum.controller.rest;

import com.auth0.jwt.interfaces.Claim;
import com.simpleforum.simpleforum.dto.RegisterUser;
import com.simpleforum.simpleforum.dto.TokenUser;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.utils.JwtUtils;
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
    public String register(@RequestBody RegisterUser user) {
        userService.register(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
        return "register success";
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody TokenUser user) {
        if (!userService.login(user.getUsername(), user.getPassword())) {
            return Map.of("status", "error", "message", "invalid username or password");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return Map.of("status", "ok", "message", "login success", "token", token);
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
