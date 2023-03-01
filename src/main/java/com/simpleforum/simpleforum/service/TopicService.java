package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Topic;

public interface TopicService {
    Topic createTopic(String name) throws RuntimeException;

    void deleteTopic(String name) throws RuntimeException;

    void updateTopic(String name, String newName) throws RuntimeException;
}
