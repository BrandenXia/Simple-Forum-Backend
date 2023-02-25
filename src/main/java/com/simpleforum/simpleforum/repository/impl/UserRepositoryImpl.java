package com.simpleforum.simpleforum.repository.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.exception.DAOException;
import com.simpleforum.simpleforum.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User createUser(User user) {
        user.setID(NanoIdUtils.randomNanoId());
        user.setRegistrationDate(new Date());
        user.setLastLoginDate(new Date());
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
    public void updateUser(User user) {
        this.entityManager.merge(user);
        logger.debug("User updated: {}", user);
    }

    @Override
    public User getUserByID(String id) throws DAOException {
        User user = this.entityManager.find(User.class, id);
        if (user != null) {
            logger.debug("User found: {}", user);
            return user;
        }
        throw new DAOException("User not found: " + id);
    }

    @Override
    public User getUserByUsername(String username) throws DAOException {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            throw new DAOException("User not found: " + username);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            throw new DAOException("User not found: " + email);
        }
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws DAOException {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
            logger.debug("User found: {}", user);
            return user;
        } catch (Exception e) {
            throw new DAOException("User not found: " + phoneNumber);
        }
    }

    @Override
    public boolean isUserExistsByID(String id) {
        return entityManager.createQuery("SELECT count(e) FROM User e WHERE e.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }


    @Override
    public boolean isUserExistsByUsername(String username) {
        return entityManager.createQuery("SELECT count(e) FROM User e WHERE e.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult() > 0;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return entityManager.createQuery("SELECT count(e) FROM User e WHERE e.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    @Override
    public boolean isUserExistsByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery("SELECT count(e) FROM User e WHERE e.phoneNumber = :phoneNumber", Long.class)
                .setParameter("phoneNumber", phoneNumber)
                .getSingleResult() > 0;
    }
}