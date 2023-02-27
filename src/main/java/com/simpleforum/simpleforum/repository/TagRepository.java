package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByNameContainingIgnoreCase(String name);

    Boolean existsByName(String name);
}
