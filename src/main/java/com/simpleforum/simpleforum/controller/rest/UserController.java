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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    @PostMapping("/login/{loginType}")
    public ResponseUtils.Response login(@PathVariable String loginType, @RequestBody UserDTO user) {
        try {
            Boolean result = userService.login(user, loginType);
            if (!result) {
                return ResponseUtils.createResponse()
                        .error()
                        .setCode(400)
                        .setMessage("invalid username or password");
            }
        } catch (Exception e) {
            return ResponseUtils.createResponse()
                    .error()
                    .setCode(400)
                    .setMessage("Invalid or not allowed login type");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("time", String.valueOf(System.currentTimeMillis()));
        String token = JwtUtils.getToken(payload);
        return ResponseUtils.createResponse()
                .success()
                .setCode(200)
                .setMessage("login success")
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
