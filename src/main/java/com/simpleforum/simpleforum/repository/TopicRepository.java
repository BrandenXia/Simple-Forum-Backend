package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
    Topic findByName(String name);

    Page<Topic> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Boolean existsByName(String name);
}
