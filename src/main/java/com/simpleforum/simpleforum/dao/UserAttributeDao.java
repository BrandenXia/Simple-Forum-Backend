package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.UserAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public interface UserAttributeDao {
    /**
     * Set user attributes
     *
     * @param user_id    user id
     * @param attributes user attributes
     * @return true if user attributes set, false if user not found
     */
    @Transactional
    Boolean setUserAttribute(String user_id, Map<String, Object> attributes);
    /**
     * Update user attributes
     * @param user_id user id
     * @param attributes user attributes
     * @return true if user attributes updated, false if user not found
     */
    @Transactional
    Boolean updateUserAttribute(String user_id, Map<String, Object> attributes);
    /**
     * Get user attributes
     * @param user_id user id
     * @return user attributes
     */
    UserAttribute getUserAttribute(String user_id);
}
