package com.simpleforum.simpleforum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    @Getter @Setter private String ID;

    @Column(unique = true, nullable = false, length = 50)
    @Getter @Setter private String username;

    @Column(unique = true, length = 50)
    @Getter @Setter private String email;

    @Column(unique = true, length = 15)
    @Getter @Setter private String phoneNumber;

    @Column(nullable = false)
    @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private Timestamp registrationDate;

    @Column(nullable = false)
    @Getter @Setter private Timestamp lastLoginDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private UserAttribute userAttribute;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<UserWarning> userWarnings;

    public User() {
    }
}
