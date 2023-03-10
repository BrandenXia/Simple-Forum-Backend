package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user_warnings")
@Data
public class UserWarning {
    @JsonIgnore
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String id;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false)
    private User moderator;
}
