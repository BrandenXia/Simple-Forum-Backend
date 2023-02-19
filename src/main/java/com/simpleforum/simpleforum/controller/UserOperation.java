package com.simpleforum.simpleforum.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserOperation {
    @PostMapping("/login")
    String login();

    @PostMapping("/register")
    String register();

    @PostMapping("/logout")
    String logout();

    @PostMapping("/update")
    String update();

    @PostMapping("/delete")
    String delete();

    @PostMapping("/get")
    String get();
}
