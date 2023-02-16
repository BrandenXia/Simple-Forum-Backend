package com.simpleforum.simpleforum.service.impl;

import com.simpleforum.simpleforum.domain.UserAttribute;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.service.UserService;
import com.simpleforum.simpleforum.service.UserAttributeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@Transactional
public class UserAttributeServiceImpl implements UserAttributeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public void setUserAttribute(String user_id, Map<String, Object> attributes) {
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setAttributes(attributes);
        User user = userService.getUserByID(user_id);
        user.setUserAttribute(userAttribute);
        userAttribute.setUser(user);
        this.entityManager.persist(user);
    }

    @Override
    public void updateUserAttribute(String user_id, Map<String, Object> attributes) {
        User user = userService.getUserByID(user_id);
        UserAttribute userAttribute = user.getUserAttribute();
        userAttribute.setAttributes(attributes);
        this.entityManager.merge(user);
    }

    @Override
    public void deleteUserAttribute(String user_id) {
        User user = userService.getUserByID(user_id);
        UserAttribute userAttribute = user.getUserAttribute();
        this.entityManager.remove(userAttribute);
    }

    @Override
    public UserAttribute getUserAttribute(String user_id) {
        User user = userService.getUserByID(user_id);
        return user.getUserAttribute();
    }
}
