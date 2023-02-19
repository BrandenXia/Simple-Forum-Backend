package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserWarningDao {
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
    UserWarning getUserWarningById(String id);

    /**
     * Get user warning by user
     *
     * @param user  user
     * @param limit limit
     * @return user warning
     */
    List<UserWarning> getUserWarningByUser(User user, Integer limit);

    List<UserWarning> getUserWarningByUser(User user);
}
