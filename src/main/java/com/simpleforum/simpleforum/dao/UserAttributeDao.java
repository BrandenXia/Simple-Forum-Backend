package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserAttributeDao {
    /**
     * Set user attribute
     * @param user user
     * @param userAttribute user attribute
     */
    @Transactional
    void setUserAttribute(User user, UserAttribute userAttribute);
}
