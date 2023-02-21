package com.simpleforum.simpleforum.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;

    private String password;

    private String newPassword;

    private String email;

    private String phoneNumber;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
