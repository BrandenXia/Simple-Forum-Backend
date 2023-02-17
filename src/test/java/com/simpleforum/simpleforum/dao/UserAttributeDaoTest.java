package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserAttribute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Transactional
class UserAttributeDaoTest {
    @Autowired
    UserDao userDao;

    @Autowired
    UserAttributeDao userAttributeDao;

    @Test
    void setUserAttribute() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setUser(user);
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("test", "test");
        userAttribute.setAttributes(attribute);
        userAttributeDao.setUserAttribute(user, userAttribute);
        assert user.getUserAttribute().equals(userAttribute);
    }
}