package com.simpleforum.simpleforum.repository.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import com.simpleforum.simpleforum.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {
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
        logger.debug("User created: {}", user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        this.entityManager.remove(user);
        logger.debug("User deleted: {}", user);
    }

    @Override
    @Transactional
    public void updateUser(User user, String username, String password, String email, String phoneNumber) {
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        this.entityManager.merge(user);
        logger.debug("User updated: {}", user);
    }

    @Override
    public User getUserByID(String id) {
        User user = this.entityManager.find(User.class, id);
        if (user != null) {
            logger.debug("User found: {}", user);
            return user;
        } else {
            logger.debug("User not found: {}", id);
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            logger.debug("User not found: {}", username);
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            logger.debug("User not found: {}", email);
            return null;
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            logger.debug("User not found: {}", phoneNumber);
            return null;
        }
    }
}