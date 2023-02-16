package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.UserAttribute;

import java.util.Map;

public interface UserAttributeDao {
    /**
     * Set user attributes
     *
     * @param user_id    user id
     * @param attributes user attributes
     * @return true if user attributes set, false if user not found
     */
    Boolean setUserAttribute(String user_id, Map<String, Object> attributes);
    /**
     * Update user attributes
     *
     * @param user_id    user id
     * @param attributes user attributes
     * @return true if user attributes updated, false if user not found
     */
    Boolean updateUserAttribute(String user_id, Map<String, Object> attributes);
    /**
     * Get user attributes
     * @param user_id user id
     * @return user attributes
     */
    UserAttribute getUserAttribute(String user_id);
}
