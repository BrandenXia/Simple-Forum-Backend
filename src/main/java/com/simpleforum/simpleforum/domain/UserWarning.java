package com.simpleforum.simpleforum.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "user_warnings")
@EqualsAndHashCode
public class UserWarning {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    @Getter
    @Setter
    private String ID;

    @Column(nullable = false)
    @Getter
    @Setter
    private String reason;

    @Column(nullable = false)
    @Getter
    @Setter
    private Timestamp date;

    @Column(unique = true, nullable = false, length = 21)
    @Getter
    @Setter
    private String moderatorID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    public UserWarning() {
    }
}
