package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.UserAttribute;

import java.util.Map;

public interface UserAttributeService {
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
     * Delete user attributes
     *
     * @param user_id user id
     * @return true if user attributes deleted, false if user not found
     */
    Boolean deleteUserAttribute(String user_id);
    /**
     * Get user attributes
     * @param user_id user id
     * @return user attributes
     */
    UserAttribute getUserAttribute(String user_id);
}
