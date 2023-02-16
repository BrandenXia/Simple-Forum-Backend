package com.simpleforum.simpleforum.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public User createUser(String username, String password, String email, String phoneNumber) {
        User user = new User();
        user.setID(NanoIdUtils.randomNanoId());
        user.setUsername(username);
        user.setPassword(password);
        List<UserWarning> userWarnings = new ArrayList<>();
        user.setUserWarnings(userWarnings);
        if (email != null) {
            user.setEmail(email);
        }
        if (phoneNumber != null) {
            user.setPhoneNumber(phoneNumber);
        }
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        user.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
        this.entityManager.persist(user);
        logger.info("User created: " + user);
        return user;
    }

    public Boolean deleteUser(String id) {
        User user = getUserByID(id);
        if (user != null) {
            this.entityManager.remove(user);
            logger.info("User deleted: " + user);
            return true;
        }
        return false;
    }

    public Boolean updateUser(String id, String username, String password, String email, String phoneNumber) {
        User user = getUserByID(id);
        if (user != null) {
            if (username != null) {
                user.setUsername(username);
            }
            if (password != null) {
                user.setPassword(password);
            }
            if (email != null) {
                user.setEmail(email);
            }
            if (phoneNumber != null) {
                user.setPhoneNumber(phoneNumber);
            }
            this.entityManager.merge(user);
            logger.info("User updated: " + user);
            return true;
        } else {
            logger.info("User not updated: " + id + " " + username + " " + password + " " + email + " " + phoneNumber);
            return false;
        }
    }

    public User getUserByID(String id) {
        User user = this.entityManager.find(User.class, id);
        if (user != null) {
            logger.info("User found: " + user);
            return user;
        } else {
            logger.info("User not found: " + id);
            return null;
        }
    }

    public User getUserByUsername(String username) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: " + user);
            return user;
        } else {
            logger.info("User not found: " + username);
            return null;
        }
    }

    public User getUserByEmail(String email) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: " + user);
            return user;
        } else {
            logger.info("User not found: " + email);
            return null;
        }
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult();
        if (user != null) {
            logger.info("User found: " + user);
            return user;
        } else {
            logger.info("User not found: " + phoneNumber);
            return null;
        }
    }
}