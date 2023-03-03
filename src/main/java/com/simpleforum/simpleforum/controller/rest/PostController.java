package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.dto.ResponseDTO;
import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.exception.PermissionException;
import com.simpleforum.simpleforum.exception.RequestBodyException;
import com.simpleforum.simpleforum.exception.TokenException;
import com.simpleforum.simpleforum.service.PermissionService;
import com.simpleforum.simpleforum.service.PostService;
import com.simpleforum.simpleforum.service.TopicService;
import com.simpleforum.simpleforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    private final UserService userService;

    private final PostService postService;

    private final TopicService topicService;

    private final PermissionService permissionService;

    @Autowired
    public PostController(PostService postService, UserService userService, TopicService topicService, PermissionService permissionService) {
        this.postService = postService;
        this.userService = userService;
        this.topicService = topicService;
        this.permissionService = permissionService;
    }

    @PostMapping("/{topicID}/create")
    public ResponseDTO createPost(@RequestHeader("token") String token, @RequestBody Post post, @PathVariable("topicID") String topicID) {
        User user = userService.getCurrentUser(token);
        if (user == null) {
            throw new TokenException("invalid token");
        }
        if (post.getTitle() == null || post.getTitle().length() == 0) {
            throw new RequestBodyException("title is required");
        }
        if (post.getContent() == null || post.getContent().length() == 0) {
            throw new RequestBodyException("content is required");
        }
        Topic topic = topicService.getTopic(topicID);
        if (!permissionService.isUserAllowedToWriteInTopic(user, topic)) {
            throw new PermissionException("Permission denied");
        }
        postService.createPost(post.getTitle(), post.getContent(), user, post.getTags(), topic);
        return ResponseDTO.builder()
                .status(200)
                .data(post)
                .build();
    }
}
