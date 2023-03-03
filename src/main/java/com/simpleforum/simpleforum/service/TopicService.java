package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.exception.AlreadyExistException;
import com.simpleforum.simpleforum.exception.NotFoundException;

public interface TopicService {
    Topic createTopic(String name) throws AlreadyExistException;

    void deleteTopic(String name) throws NotFoundException;

    void updateTopic(String name, String newName) throws NotFoundException, AlreadyExistException;

    Topic getTopic(String ID) throws NotFoundException;
}
