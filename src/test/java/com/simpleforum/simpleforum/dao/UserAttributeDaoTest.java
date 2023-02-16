package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        Map<String, Object> attribute = Map.of("test", "test");
        assert userAttributeDao.setUserAttribute(user.getID(), attribute);
        assert userAttributeDao.getUserAttribute(user.getID()).getAttributes().equals(attribute);
    }

    @Test
    void updateUserAttribute() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        Map<String, Object> attribute = Map.of("test", "test");
        userAttributeDao.setUserAttribute(user.getID(), attribute);
        Map<String, Object> attribute2 = Map.of("test2", "test2");
        assert userAttributeDao.updateUserAttribute(user.getID(), attribute2);
        assert userAttributeDao.getUserAttribute(user.getID()).getAttributes().equals(attribute2);
    }

    @Test
    void getUserAttribute() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        Map<String, Object> attribute = Map.of("test", "test");
        userAttributeDao.setUserAttribute(user.getID(), attribute);
        assert userAttributeDao.getUserAttribute(user.getID()).getAttributes().equals(attribute);
    }
}