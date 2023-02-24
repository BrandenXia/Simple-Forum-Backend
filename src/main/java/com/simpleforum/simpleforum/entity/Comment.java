package com.simpleforum.simpleforum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String id;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private Timestamp created_time;

    @Column(nullable = false)
    private Timestamp updated_time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
