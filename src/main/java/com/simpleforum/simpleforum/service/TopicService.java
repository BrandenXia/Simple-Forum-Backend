package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Topic;

public interface TopicService {
    Topic createTopic(String name);
    void deleteTopic(String name);
    void updateTopic(String name, String newName);
}
