package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Topic;

import java.util.List;

public interface TopicService {
    Topic createTopic(String name);
    void deleteTopic(String name);
    void updateTopic(String name, String newName);
    List<Topic> getTopics();
}
