package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
    Topic findByNameContainingIgnoreCase(String name);

    Boolean existsByName(String name);
}
