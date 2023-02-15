package com.simpleforum.simpleforum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    @Setter @Getter private String ID;

    @Column(unique = true, nullable = false, length = 50)
    @Setter @Getter private String username;

    @Column(unique = true, length = 50)
    @Setter @Getter private String email;

    @Column(unique = true, length = 15)
    @Setter @Getter private String phoneNumber;

    @Column(nullable = false)
    @Setter private String password;

    @Column(nullable = false)
    @Getter @Setter private Timestamp registrationDate;

    @Column(nullable = false)
    @Getter @Setter private Timestamp lastLoginDate;
}
