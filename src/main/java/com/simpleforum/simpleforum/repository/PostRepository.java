package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,String> {
    Page<Post> findByOrderByCreatedTimeDesc(Pageable pageable);
    Page<Post> findByUserOrderByCreatedTime(Pageable pageable, User user);
}
