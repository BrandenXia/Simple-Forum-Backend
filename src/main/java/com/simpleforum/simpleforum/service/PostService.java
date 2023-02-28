package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.Tag;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    Post createPost(String title, String content, User user, List<Tag> tags, Topic topic);

    Page<Post> searchPosts(String search, Pageable pageable);

    Page<Post> getRecentPost(int page, int size);
}
