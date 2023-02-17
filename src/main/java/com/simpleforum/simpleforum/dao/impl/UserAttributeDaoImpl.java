package com.simpleforum.simpleforum.dao.impl;

import com.simpleforum.simpleforum.dao.UserAttributeDao;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAttributeDaoImpl implements UserAttributeDao {
    @PersistenceContext
    private EntityManager entityManager;

    final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public void setUserAttribute(User user, UserAttribute userAttribute) {
        user.setUserAttribute(userAttribute);
        entityManager.persist(userAttribute);
        logger.info("User attribute created");
    }
}
