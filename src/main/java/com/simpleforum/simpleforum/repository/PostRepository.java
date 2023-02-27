package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,String> {
    List<Post> findByUser(User user);

    List<Post> findByTitleContainingIgnoreCase(String title);

    List<Post> findByTitleContainingIgnoreCaseAndUser(String title, User user);

    List<Post> findByContentContainingIgnoreCase(String content);

    List<Post> findByContentContainingIgnoreCaseAndUser(String content, User user);

    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseAndUser(String title, String content, User user);

    List<Post> findByCreatedTimeBetween(Date date1, Date date2);

    List<Post> findByCreatedTimeBetweenAndUser(Date date1, Date date2, User user);

    List<Post> findByCreatedTimeAfter(Date date);

    List<Post> findByCreatedTimeAfterAndUser(Date date, User user);

    List<Post> findByCreatedTimeBefore(Date date);

    List<Post> findByCreatedTimeBeforeAndUser(Date date, User user);

    List<Post> findByDeleted(boolean deleted);

    List<Post> findByDeletedAndUser(boolean deleted, User user);

    List<Post> findByDeletedAndTitleContainingIgnoreCase(boolean deleted, String title);

    List<Post> findByDeletedAndTitleContainingIgnoreCaseAndUser(boolean deleted, String title, User user);

    List<Post> findByDeletedOrContentContainingIgnoreCase(boolean deleted, String content);

    List<Post> findByDeletedOrContentContainingIgnoreCaseAndUser(boolean deleted, String content, User user);
}
