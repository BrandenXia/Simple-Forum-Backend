package com.simpleforum.simpleforum.repository.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
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

import java.util.Date;
import java.util.List;

@Repository
public class UserWarningRepositoryImpl implements UserWarningRepository {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserWarning createWarning(UserWarning userWarning) {
        userWarning.setId(NanoIdUtils.randomNanoId());
        userWarning.setDate(new Date());
        entityManager.persist(userWarning);
        logger.debug("User warning created: {}", userWarning);
        return userWarning;
    }

    @Override
    @Transactional
    public void deleteWarning(UserWarning userWarning) {
        logger.debug("User warning deleted: {}", userWarning);
        entityManager.remove(userWarning);
    }

    @Override
    public UserWarning getUserWarningById(String id) throws DAOException {
        UserWarning userWarning = entityManager.find(UserWarning.class, id);
        if (userWarning != null) {
            logger.debug("User warning found: {}", userWarning);
            return userWarning;
        }
        throw new DAOException("User warning not found: " + id);
    }

    @Override
    public List<UserWarning> getUserWarningsByUser(User user) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                    .setParameter("user", user)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ user);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByUser(User user, int limit) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.user = :user", UserWarning.class)
                    .setParameter("user", user)
                    .setMaxResults(limit)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ user);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByModerator(User moderator) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.moderator = :moderator", UserWarning.class)
                    .setParameter("moderator", moderator)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ moderator);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByModerator(User moderator, int limit) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.moderator = :moderator", UserWarning.class)
                    .setParameter("moderator", moderator)
                    .setMaxResults(limit)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ moderator);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByDate(long date) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.date = :date", UserWarning.class)
                    .setParameter("date", new Date(date))
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ date);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByDate(long date, int limit) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.date = :date", UserWarning.class)
                    .setParameter("date", new Date(date))
                    .setMaxResults(limit)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ date);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByDate(long date1, long date2) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.date BETWEEN :date1 AND :date2", UserWarning.class)
                    .setParameter("date1", new Date(date1))
                    .setParameter("date2", new Date(date2))
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ date1 + " - " + date2);
        }
    }

    @Override
    public List<UserWarning> getUserWarningsByDate(long date1, long date2, int limit) throws DAOException {
        try {
            List<UserWarning> userWarnings = entityManager.createQuery("SELECT uw FROM UserWarning uw WHERE uw.date BETWEEN :date1 AND :date2", UserWarning.class)
                    .setParameter("date1", new Date(date1))
                    .setParameter("date2", new Date(date2))
                    .setMaxResults(limit)
                    .getResultList();
            logger.debug("User warnings found: {}", userWarnings);
            return userWarnings;
        } catch (Exception e) {
            throw new DAOException("User warnings not found: "+ date1 + " - " + date2);
        }
    }
}
