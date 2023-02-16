package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;

public interface UserWarningService {
    Boolean createWarning(String user_id, String reason, String moderator_id);
    Boolean deleteWarning(String id);
    Boolean updateWarning(String id, String reason, User user, String moderator_id);
    UserWarning getWarningByID(String id);
}
