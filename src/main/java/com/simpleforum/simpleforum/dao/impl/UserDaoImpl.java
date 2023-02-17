package com.simpleforum.simpleforum.dao.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.dao.UserDao;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public User createUser(String username, String password, String email, String phoneNumber) {
        User user = new User();
        user.setID(NanoIdUtils.randomNanoId());
        user.setUsername(username);
        user.setPassword(password);
        List<UserWarning> userWarnings = new ArrayList<>();
        user.setUserWarnings(userWarnings);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
        this.entityManager.persist(user);
        logger.info("User created: {}", user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        this.entityManager.remove(user);
        logger.info("User deleted: {}", user);
    }

    @Override
    @Transactional
    public void updateUser(User user, String username, String password, String email, String phoneNumber) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        this.entityManager.merge(user);
        logger.info("User updated: {}", user);
    }

    @Override
    public User getUserByID(String id) {
        User user = this.entityManager.find(User.class, id);
        if (user != null) {
            logger.info("User found: {}", user);
            return user;
        } else {
            logger.error("User not found: {}", id);
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: {}", user);
            return user;
        } else {
            logger.error("User not found: {}", username);
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: {}", user);
            return user;
        } else {
            logger.error("User not found: {}", email);
            return null;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: {}", user);
            return user;
        } else {
            logger.error("User not found: {}", phoneNumber);
            return null;
        }
    }
}