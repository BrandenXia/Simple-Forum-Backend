package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserAttribute;
import org.springframework.transaction.annotation.Transactional;

public interface UserAttributeRepository {
    /**
     * Set user attribute
     *
     * @param user          user
     * @param userAttribute user attribute
     */
    @Transactional
    void setUserAttribute(User user, UserAttribute userAttribute);
}
