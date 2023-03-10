package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Comment;
import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, String> {
    Page<Comment> findByPost(Post post, Pageable pageable);

    Page<Comment> findByUser(User user, Pageable pageable);

    Page<Comment> findByPostAndUser(Post post, User user, Pageable pageable);
}
