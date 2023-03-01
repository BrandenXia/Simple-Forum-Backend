package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
    Page<Post> findByUserOrderByCreatedTime(Pageable pageable, User user);

    Page<Post> findByDeleted(Boolean deleted, Pageable pageable);

    Page<Post> findByTopicAndDeleted(Topic topic, Boolean deleted, Pageable pageable);

    Page<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseAndDeleted(String title, String content, Boolean deleted, Pageable pageable);
}
