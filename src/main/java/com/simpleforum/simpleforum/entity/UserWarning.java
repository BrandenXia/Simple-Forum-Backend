package com.simpleforum.simpleforum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "user_warnings")
@Data
public class UserWarning {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String ID;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Timestamp date;

    @Column(unique = true, nullable = false, length = 21)
    private String moderatorID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserWarning() {
    }
}
