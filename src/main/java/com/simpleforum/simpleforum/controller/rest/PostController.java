package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.entity.Post;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.service.PermissionService;
import com.simpleforum.simpleforum.service.PostService;
import com.simpleforum.simpleforum.service.TopicService;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.util.ResponseUtils;
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
    public ResponseUtils.Response createPost(@RequestHeader("token") String token, @RequestBody Post post, @PathVariable("topicID") String topicID) {
        User user = userService.getCurrentUser(token);
        if (user == null) {
            return ResponseUtils.createResponse()
                    .error(400, "invalid token");
        }
        if (post.getTitle() == null || post.getTitle().length() == 0) {
            return ResponseUtils.createResponse()
                    .error(400, "title is required");
        }
        if (post.getContent() == null || post.getContent().length() == 0) {
            return ResponseUtils.createResponse()
                    .error(400, "content is required");
        }
        try {
            Topic topic = topicService.getTopic(topicID);
            permissionService.isUserAllowedToWriteInTopic(user, topic);
            postService.createPost(post.getTitle(), post.getContent(), user, post.getTags(), topic);
            return ResponseUtils.createResponse()
                    .success()
                    .setData(post);
        } catch (Exception e) {
            return ResponseUtils.createResponse()
                    .error(400, e.getMessage());
        }
    }
}
