package com.simpleforum.simpleforum.dao.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.dao.UserWarningDao;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import com.simpleforum.simpleforum.dao.UserDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Component
@Transactional
public class UserWarningDaoImpl implements UserWarningDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserDao userDao;

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Boolean createWarning(String user_id, String reason, String moderator_id) {
        User user = userDao.getUserByID(user_id);
        User moderator = userDao.getUserByID(moderator_id);
        if (user != null && moderator != null) {
            UserWarning warning = new UserWarning();
            warning.setID(NanoIdUtils.randomNanoId());
            warning.setReason(reason);
            warning.setModeratorID(moderator_id);
            warning.setDate(new Timestamp(System.currentTimeMillis()));
            warning.setUser(user);
            user.getUserWarnings().add(warning);
            logger.info("Warning added: " + warning);
            return true;
        } else {
            logger.info("Warning not added: " + user_id + " " + reason + " " + moderator_id);
            return false;
        }
    }

    @Override
    public Boolean deleteWarning(String id) {
        UserWarning warning = getWarningByID(id);
        if (warning != null) {
            this.entityManager.remove(warning);
            logger.info("Warning deleted: " + warning);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateWarning(String id, String reason, User user, String moderator_id) {
        UserWarning warning = getWarningByID(id);
        if (warning != null) {
            if (reason != null) {
                warning.setReason(reason);
            }
            if (user != null) {
                warning.setUser(user);
            }
            if (moderator_id != null) {
                User moderator = userDao.getUserByID(moderator_id);
                if (moderator != null) {
                    warning.setModeratorID(moderator_id);
                }
            }
            logger.info("Warning updated: " + warning);
            return true;
        }
        return false;
    }

    @Override
    public UserWarning getWarningByID(String id) {
        UserWarning warning = this.entityManager.find(UserWarning.class, id);
        if (warning != null) {
            logger.info("Warning found: " + warning);
            return warning;
        } else {
            logger.info("Warning not found: " + id);
            return null;
        }
    }
}
