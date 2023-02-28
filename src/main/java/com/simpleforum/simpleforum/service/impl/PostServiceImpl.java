package com.simpleforum.simpleforum.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.Tag;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.repository.PostRepository;
import com.simpleforum.simpleforum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(String title, String content, User user, List<Tag> tags, Topic topic) {
        Post post = new Post();
        post.setId(NanoIdUtils.randomNanoId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedTime(new Date());
        post.setUpdatedTime(new Date());
        post.setLastCommentTime(new Date());
        post.setLocked(false);
        post.setDeleted(false);
        post.setUser(user);
        post.setTopic(topic);
        post.setTags(tags);
        return postRepository.save(post);
    }

    @Override
    public Page<Post> searchPosts(String search, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseAndDeleted(search, search, false , pageable);
    }

    @Override
    public Page<Post> getRecentPost(int page, int size) {
        return postRepository.findByDeleted(false, PageRequest.of(page, size, Sort.by("createdTime").descending()));
    }
}
