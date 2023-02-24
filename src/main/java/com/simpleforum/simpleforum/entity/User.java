package com.simpleforum.simpleforum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String ID;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Timestamp registrationDate;

    @Column(nullable = false)
    private Timestamp lastLoginDate;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}