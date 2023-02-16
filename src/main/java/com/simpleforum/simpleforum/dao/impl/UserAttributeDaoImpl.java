package com.simpleforum.simpleforum.dao.impl;

import com.simpleforum.simpleforum.dao.UserAttributeDao;
import com.simpleforum.simpleforum.dao.UserDao;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public class UserAttributeDaoImpl implements UserAttributeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserDao userDao;

    final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public Boolean setUserAttribute(String user_id, Map<String, Object> attributes) {
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setAttributes(attributes);
        User user = userDao.getUserByID(user_id);
        if (user != null) {
            user.setUserAttribute(userAttribute);
            userAttribute.setUser(user);
            this.entityManager.persist(user);
            logger.info("UserAttribute set for user with id: {}", user_id);
            return true;
        } else {
            logger.error("User not found for id: {}", user_id);
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateUserAttribute(String user_id, Map<String, Object> attributes) {
        User user = userDao.getUserByID(user_id);
        if (user != null) {
            UserAttribute userAttribute = user.getUserAttribute();
            userAttribute.setAttributes(attributes);
            this.entityManager.merge(user);
            logger.info("UserAttribute updated for user with id: {}", user_id);
            return true;
        } else {
            logger.error("User not found for id: {}", user_id);
            return false;
        }

    }

    @Override
    public UserAttribute getUserAttribute(String user_id) {
        User user = userDao.getUserByID(user_id);
        if (user != null) {
            UserAttribute userAttribute = user.getUserAttribute();
            logger.info("UserAttribute get for user with id: {}", user_id);
            return userAttribute;
        } else {
            logger.error("User not found for id: {}", user_id);
            return null;
        }
    }
}
