package com.simpleforum.simpleforum.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_warnings")
public class UserWarning {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    @Getter @Setter private String ID;

    @Column(nullable = false)
    @Getter @Setter private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;

    public UserWarning() {
    }
}
