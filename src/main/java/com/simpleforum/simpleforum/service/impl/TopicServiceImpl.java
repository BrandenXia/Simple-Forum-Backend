package com.simpleforum.simpleforum.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.repository.PermissionRepository;
import com.simpleforum.simpleforum.repository.TopicRepository;
import com.simpleforum.simpleforum.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final PermissionRepository permissionRepository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, PermissionRepository permissionRepository) {
        this.topicRepository = topicRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Topic createTopic(String name) throws RuntimeException {
        if (topicRepository.existsByName(name)) {
            throw new RuntimeException("Topic already exists");
        }
        Topic topic = new Topic();
        topic.setId(NanoIdUtils.randomNanoId());
        topic.setName(name);
        topicRepository.save(topic);
        Permission readPermission = new Permission();
        readPermission.setId(NanoIdUtils.randomNanoId());
        readPermission.setName(topic.getReadPermission());
        permissionRepository.save(readPermission);
        Permission writePermission = new Permission();
        writePermission.setId(NanoIdUtils.randomNanoId());
        writePermission.setName(topic.getWritePermission());
        permissionRepository.save(writePermission);
        Permission deletePermission = new Permission();
        deletePermission.setId(NanoIdUtils.randomNanoId());
        deletePermission.setName(topic.getDeletePermission());
        permissionRepository.save(deletePermission);
        return topic;
    }

    @Override
    public void deleteTopic(String name) throws RuntimeException {
        Topic topic = topicRepository.findByName(name);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        Permission readPermission = permissionRepository.findByName(topic.getReadPermission());
        if (readPermission == null) {
            logger.warn("Read permission not found");
        } else {
            permissionRepository.delete(readPermission);
        }
        Permission writePermission = permissionRepository.findByName(topic.getWritePermission());
        if (writePermission == null) {
            logger.warn("Write permission not found");
        } else {
            permissionRepository.delete(writePermission);
        }
        Permission deletePermission = permissionRepository.findByName(topic.getDeletePermission());
        if (deletePermission == null) {
            logger.warn("Delete permission not found");
        } else {
            permissionRepository.delete(deletePermission);
        }
        topicRepository.delete(topic);
    }

    @Override
    public void updateTopic(String name, String newName) throws RuntimeException {
        Topic topic = topicRepository.findByName(name);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        topic.setName(newName);
        topicRepository.save(topic);
        Permission readPermission = permissionRepository.findByName(topic.getReadPermission());
        if (readPermission == null) {
            logger.warn("Read permission not found");
        } else {
            readPermission.setName("read-" + newName);
            permissionRepository.save(readPermission);
        }
        Permission writePermission = permissionRepository.findByName(topic.getWritePermission());
        if (writePermission == null) {
            logger.warn("Write permission not found");
        } else {
            writePermission.setName("write-" + newName);
            permissionRepository.save(writePermission);
        }
        Permission deletePermission = permissionRepository.findByName(topic.getDeletePermission());
        if (deletePermission == null) {
            logger.warn("Delete permission not found");
        } else {
            deletePermission.setName("delete-" + newName);
            permissionRepository.save(deletePermission);
        }
    }

    @Override
    public Topic getTopic(String ID) throws RuntimeException {
        Topic topic = topicRepository.findById(ID).orElse(null);
        if (topic == null) {
            throw new RuntimeException("Topic not found");
        }
        return topic;
    }
}
