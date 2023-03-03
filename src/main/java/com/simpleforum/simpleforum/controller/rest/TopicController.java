package com.simpleforum.simpleforum.controller.rest;

import com.simpleforum.simpleforum.dto.ResponseDTO;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.service.TopicService;
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
    public ResponseDTO createTopic(@RequestBody Topic topic) {
        Topic createdTopic = topicService.createTopic(topic.getName());
        return ResponseDTO.builder()
                .status(200)
                .data(createdTopic)
                .build();
    }

    @PostMapping("/delete")
    public ResponseDTO deleteTopic(@RequestBody Topic topic) {
        topicService.deleteTopic(topic.getName());
        return ResponseDTO.builder()
                .status(200)
                .build();
    }

    @PostMapping("/update")
    public ResponseDTO updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic.getName(), topic.getNewName());
        return ResponseDTO.builder()
                .status(200)
                .build();
    }
}
