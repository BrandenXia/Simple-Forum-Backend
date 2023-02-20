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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Timestamp registrationDate;

    @Column(nullable = false)
    private Timestamp lastLoginDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserAttribute userAttribute;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserWarning> userWarnings;

    public User() {
    }
}