package com.simpleforum.simpleforum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @JsonIgnore
    @Id
    @Column(unique = true, nullable = false, updatable = false, length = 21)
    private String id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Post> posts;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newName;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String readPermission;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String writePermission;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String deletePermission;

    public String getReadPermission() {
        return "read-" + name;
    }

    public String getWritePermission() {
        return "write-" + name;
    }

    public String getDeletePermission() {
        return "delete-" + name;
    }
}
