package com.simpleforum.simpleforum.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Post> posts;
}
