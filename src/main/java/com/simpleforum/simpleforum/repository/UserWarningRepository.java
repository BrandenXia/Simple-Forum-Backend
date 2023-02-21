package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import com.simpleforum.simpleforum.exception.DAOException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserWarningRepository {
    /**
     * Create user warning
     *
     * @param user        user
     * @param userWarning user warning
     */
    @Transactional
    void addUserWarning(User user, UserWarning userWarning);

    /**
     * Delete user warning
     *
     * @param user        user
     * @param userWarning user warning
     */
    @Transactional
    void deleteUserWarning(User user, UserWarning userWarning);

    /**
     * Get user warning by id
     *
     * @param id id
     * @return user warning
     */
    UserWarning getUserWarningById(String id) throws DAOException;

    /**
     * Get user warning by user
     *
     * @param user  user
     * @param limit limit
     * @return user warning
     */
    List<UserWarning> getUserWarningByUser(User user, Integer limit) throws DAOException;

    List<UserWarning> getUserWarningByUser(User user) throws DAOException;
}
