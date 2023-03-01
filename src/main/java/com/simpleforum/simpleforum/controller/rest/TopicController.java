package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.service.TopicService;
import com.simpleforum.simpleforum.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/create")
    public ResponseUtils.Response createTopic(@RequestBody Topic topic) {
        try {
            topicService.createTopic(topic.getName());
        } catch (RuntimeException e) {
            return ResponseUtils.createResponse()
                    .error(400, e.getMessage());
        }
        return ResponseUtils.createResponse()
                .success();
    }

    @PostMapping("/delete")
    public ResponseUtils.Response deleteTopic(@RequestBody Topic topic) {
        try {
            topicService.deleteTopic(topic.getName());
        } catch (RuntimeException e) {
            return ResponseUtils.createResponse()
                    .error(400, e.getMessage());
        }
        return ResponseUtils.createResponse()
                .success();
    }

    @PostMapping("/update")
    public ResponseUtils.Response updateTopic(@RequestBody Topic topic) {
        try {
            topicService.updateTopic(topic.getName(), topic.getNewName());
        } catch (RuntimeException e) {
            return ResponseUtils.createResponse()
                    .error(400, e.getMessage());
        }
        return ResponseUtils.createResponse()
                .success();
    }
}
