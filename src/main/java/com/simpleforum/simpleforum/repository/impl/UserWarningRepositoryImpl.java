package com.simpleforum.simpleforum.repository.impl;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import com.simpleforum.simpleforum.exception.DAOException;
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
    public UserWarning getUserWarningById(String id) throws DAOException {
        logger.debug("Getting user warning by id " + id);
        UserWarning userWarning = entityManager.find(UserWarning.class, id);
        if (userWarning != null) {
            logger.debug("User warning found: " + userWarning);
            return userWarning;
        } else {
            logger.debug("User warning not found");
            throw new DAOException("User warning not found");
        }
    }

    @Override
    public List<UserWarning> getUserWarningByUser(User user, Integer limit) throws DAOException {
        logger.debug("Getting user warning by user " + user.getUsername());
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                    .setParameter("user", user)
                    .setMaxResults(limit)
                    .getResultList();
            logger.debug("User warnings found: " + userWarnings);
            return userWarnings;
        } catch (Exception e) {
            logger.debug("User warning not found");
            throw new DAOException("User warning not found");
        }
    }

    @Override
    public List<UserWarning> getUserWarningByUser(User user) throws DAOException {
        logger.debug("Getting user warning by user " + user.getUsername());
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                    .setParameter("user", user)
                    .getResultList();
            logger.debug("User warnings found: " + userWarnings);
            return userWarnings;
        } catch (Exception e) {
            logger.debug("User warning not found");
            throw new DAOException("User warning not found");
        }
    }
}
