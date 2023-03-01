package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @JsonIgnore
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String ID;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, length = 50)
    private String email;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;
}