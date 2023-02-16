package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;

public interface UserWarningService {
    /**
     * Create a new warning, return the warning if success, return null if fail
     * @param user_id user id
     * @param reason reason
     * @param moderator_id moderator id
     * @return UserWarning
     */
    Boolean createWarning(String user_id, String reason, String moderator_id);
    /**
     * Delete a warning, return true if success, return false if fail
     * @param id warning id
     * @return Boolean
     */
    Boolean deleteWarning(String id);
    /**
     * Update a warning, return true if success, return false if fail
     * @param id warning id
     * @param reason reason
     * @param user user
     * @param moderator_id moderator id
     * @return Boolean
     */
    Boolean updateWarning(String id, String reason, User user, String moderator_id);
    /**
     * Get a warning by id, return the warning if success, return null if fail
     * @param id warning id
     * @return UserWarning
     */
    UserWarning getWarningByID(String id);
}
