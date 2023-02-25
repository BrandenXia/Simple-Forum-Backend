package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import com.simpleforum.simpleforum.exception.DAOException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserWarningRepository {
    /**
     * Create a new user warning
     * @param userWarning the user warning to create
     * @return the created user warning
     */
    @Transactional
    UserWarning createWarning(UserWarning userWarning);

    @Transactional
    void deleteWarning(UserWarning userWarning);

    UserWarning getUserWarningById(String id) throws DAOException;

    List<UserWarning> getUserWarningsByUser(User user) throws DAOException;

    List<UserWarning> getUserWarningsByUser(User user, int limit) throws DAOException;

    List<UserWarning> getUserWarningsByModerator(User moderator) throws DAOException;

    List<UserWarning> getUserWarningsByModerator(User moderator, int limit) throws DAOException;

    List<UserWarning> getUserWarningsByDate(long date) throws DAOException;

    List<UserWarning> getUserWarningsByDate(long date, int limit) throws DAOException;

    List<UserWarning> getUserWarningsByDate(long date1, long date2) throws DAOException;

    List<UserWarning> getUserWarningsByDate(long date1, long date2, int limit) throws DAOException;
}
