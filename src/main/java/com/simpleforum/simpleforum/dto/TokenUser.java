package com.simpleforum.simpleforum.dto;

import lombok.Getter;
import lombok.Setter;

public class TokenUser {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    public TokenUser() {
    }

    public TokenUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}