package com.simpleforum.simpleforum.dto;

import lombok.Getter;
import lombok.Setter;

public class RegisterUser {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    public RegisterUser() {
    }

    public RegisterUser(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
