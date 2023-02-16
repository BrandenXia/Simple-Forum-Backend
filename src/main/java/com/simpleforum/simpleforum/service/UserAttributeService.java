package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.UserAttribute;
import com.simpleforum.simpleforum.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@Transactional
public class UserAttributeService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserService userService;

    public void addUserAttribute(String user_id, Map<String, String> attributes) {
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setAttributes(attributes);
        User user = userService.getUserByID(user_id);
        user.setUserAttribute(userAttribute);
        userAttribute.setUser(user);
        this.entityManager.persist(user);
    }

    public void updateUserAttribute(String user_id, Map<String, String> attributes) {
        User user = userService.getUserByID(user_id);
        UserAttribute userAttribute = user.getUserAttribute();
        userAttribute.setAttributes(attributes);
        this.entityManager.merge(user);
    }
}
