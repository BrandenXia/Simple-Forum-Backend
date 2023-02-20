package com.simpleforum.simpleforum.repository.impl;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserAttribute;
import com.simpleforum.simpleforum.repository.UserAttributeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAttributeRepositoryImpl implements UserAttributeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public void setUserAttribute(User user, UserAttribute userAttribute) {
        user.setUserAttribute(userAttribute);
        entityManager.persist(userAttribute);
        logger.debug("User attribute created");
    }
}
