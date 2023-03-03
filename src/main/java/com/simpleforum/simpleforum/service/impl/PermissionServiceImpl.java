package com.simpleforum.simpleforum.service.impl;

import com.simpleforum.simpleforum.entity.Permission;
import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.repository.PermissionRepository;
import com.simpleforum.simpleforum.service.PermissionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Boolean isUserAllowedToReadInTopic(User user, Topic topic) {
        Permission readPermission = permissionRepository.findByName(topic.getReadPermission());
        if (readPermission == null) {
            logger.warn("Read permission for topic {} is null", topic.getName());
            return false;
        }
        return user.getRoles().stream().anyMatch(role -> role.getPermissions().contains(readPermission));
    }

    @Override
    public Boolean isUserAllowedToWriteInTopic(User user, Topic topic) {
        Permission writePermission = permissionRepository.findByName(topic.getWritePermission());
        if (writePermission == null) {
            logger.warn("Write permission for topic {} is null", topic.getName());
            return false;
        }
        return user.getRoles().stream().anyMatch(role -> role.getPermissions().contains(writePermission));
    }

    @Override
    public Boolean isUserAllowedToDeleteInTopic(User user, Topic topic) {
        Permission deletePermission = permissionRepository.findByName(topic.getDeletePermission());
        if (deletePermission == null) {
            logger.warn("Delete permission for topic {} is null", topic.getName());
            return false;
        }
        return user.getRoles().stream().anyMatch(role -> role.getPermissions().contains(deletePermission));
    }
}
