package com.simpleforum.simpleforum.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.repository.PermissionRepository;
import com.simpleforum.simpleforum.repository.TopicRepository;
import com.simpleforum.simpleforum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    private final PermissionRepository permissionRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, PermissionRepository permissionRepository) {
        this.topicRepository = topicRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Topic createTopic(String name) {
        if (topicRepository.existsByName(name)) {
            throw new RuntimeException("Topic already exists");
        }
        Topic topic = new Topic();
        topic.setId(NanoIdUtils.randomNanoId());
        topic.setName(name);
        topicRepository.save(topic);
        Permission readPermission = new Permission();
        readPermission.setId(NanoIdUtils.randomNanoId());
        readPermission.setName("read-" + name);
        topicRepository.save(topic);
        Permission writePermission = new Permission();
        writePermission.setId(NanoIdUtils.randomNanoId());
        writePermission.setName("write-" + name);
        topicRepository.save(topic);
        Permission deletePermission = new Permission();
        deletePermission.setId(NanoIdUtils.randomNanoId());
        deletePermission.setName("delete-" + name);
        topicRepository.save(topic);
        return topic;
    }

    @Override
    public void deleteTopic(String name) {
        Topic topic = topicRepository.findByName(name);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        Permission readPermission = permissionRepository.findByName("read-" + name);
        if (readPermission == null) {
            throw new RuntimeException("Read permission not found");
        }
        Permission writePermission = permissionRepository.findByName("write-" + name);
        if (writePermission == null) {
            throw new RuntimeException("Write permission not found");
        }
        Permission deletePermission = permissionRepository.findByName("delete-" + name);
        if (deletePermission == null) {
            throw new RuntimeException("Delete permission not found");
        }
        topicRepository.delete(topic);
    }

    @Override
    public void updateTopic(String name, String newName) {
        Topic topic = topicRepository.findByName(name);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        topic.setName(newName);
        topicRepository.save(topic);
    }
}
