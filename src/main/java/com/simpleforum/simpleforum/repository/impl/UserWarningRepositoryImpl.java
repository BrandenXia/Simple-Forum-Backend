package com.simpleforum.simpleforum.repository.impl;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import com.simpleforum.simpleforum.repository.UserWarningRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserWarningRepositoryImpl implements UserWarningRepository {
    @PersistenceContext
    private EntityManager entityManager;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public void addUserWarning(User user, UserWarning userWarning) {
        logger.debug("Creating user warning for user " + user.getUsername());
        entityManager.persist(userWarning);
    }

    @Override
    @Transactional
    public void deleteUserWarning(User user, UserWarning userWarning) {
        logger.debug("Deleting user warning for user " + user.getUsername());
        entityManager.remove(userWarning);
    }

    @Override
    public UserWarning getUserWarningById(String id) {
        logger.debug("Getting user warning by id " + id);
        return entityManager.find(UserWarning.class, id);
    }

    @Override
    public List<UserWarning> getUserWarningByUser(User user, Integer limit) {
        logger.debug("Getting user warning by user " + user.getUsername());
        return entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                .setParameter("user", user)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<UserWarning> getUserWarningByUser(User user) {
        logger.debug("Getting user warning by user " + user.getUsername());
        return entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                .setParameter("user", user)
                .getResultList();
    }
}
