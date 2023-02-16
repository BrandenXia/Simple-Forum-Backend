package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    void createUser() {
        userDao.createUser("test", "test", "test@test.com", "123456789");
        assert userDao.getUserByUsername("test") != null;
    }

    @Test
    void deleteUser() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        assert userDao.deleteUser(user.getID());
        assert userDao.getUserByID(user.getID()) == null;
    }

    @Test
    void updateUser() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        assert userDao.updateUser(user.getID(), "test2", "test2", "test2@test.com", "987654321");
        assert userDao.getUserByID(user.getID()).getUsername().equals("test2");
    }

    @Test
    void getUserByID() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userDao.getUserByID(user.getID());
        assert user2.equals(user);
    }

    @Test
    void getUserByUsername() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userDao.getUserByUsername(user.getUsername());
        assert user2.equals(user);
    }

    @Test
    void getUserByEmail() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userDao.getUserByEmail(user.getEmail());
        assert user2.equals(user);
    }

    @Test
    void getUserByPhoneNumber() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userDao.getUserByPhoneNumber(user.getPhoneNumber());
        assert user2.equals(user);
    }
}