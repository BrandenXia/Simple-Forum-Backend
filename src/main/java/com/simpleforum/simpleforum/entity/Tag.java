package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tag {
    @JsonIgnore
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String id;

    @Column(nullable = false, unique = true, length = 30)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}
