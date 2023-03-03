package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.entity.Topic;
import com.simpleforum.simpleforum.entity.User;

public interface PermissionService {
    Boolean isUserAllowedToReadInTopic(User user, Topic topic);
    Boolean isUserAllowedToWriteInTopic(User user, Topic topic);
    Boolean isUserAllowedToDeleteInTopic(User user, Topic topic);
}
