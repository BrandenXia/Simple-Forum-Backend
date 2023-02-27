package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Comment;
import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByUser(User user);

    List<Comment> findByContentContainingIgnoreCase(String content);

    List<Comment> findByContentContainingIgnoreCaseAndUser(String content, User user);

    List<Comment> findByPost(Post post);

    List<Comment> findByPostAndUser(Post post, User user);

    List<Comment> findByPostAndContentContainingIgnoreCase(Post post, String content);

    List<Comment> findByUserAndCreatedTimeBetween(User user, Date date1, Date date2);

    List<Comment> findByContentContainingIgnoreCaseAndCreatedTimeBetween(String content, Date date1, Date date2);

    List<Comment> findByUserAndContentContainingIgnoreCasendCreatedTimeBetween(User user, String content, Date date1, Date date2);
}
