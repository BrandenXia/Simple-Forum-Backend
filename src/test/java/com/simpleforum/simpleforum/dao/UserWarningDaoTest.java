package com.simpleforum.simpleforum.dao;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
@Transactional
class UserWarningDaoTest {
    @Autowired
    UserDao userDao;

    @Autowired
    UserWarningDao userWarningDao;

    @Test
    void addUserWarning() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningDao.addUserWarning(user, userWarning);
        assert userWarningDao.getUserWarningById(userWarning.getID()) == userWarning;
    }

    @Test
    void deleteUserWarning() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningDao.addUserWarning(user, userWarning);
        userWarningDao.deleteUserWarning(user, userWarning);
        assert userWarningDao.getUserWarningById(userWarning.getID()) == null;
    }

    @Test
    void getUserWarningById() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningDao.addUserWarning(user, userWarning);
        assert userWarningDao.getUserWarningById(userWarning.getID()) == userWarning;
    }

    @Test
    void getUserWarningByUser() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningDao.addUserWarning(user, userWarning);
        assert userWarningDao.getUserWarningByUser(user, 1).get(0) == userWarning;
    }

    @Test
    void GetUserWarningByUser() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningDao.addUserWarning(user, userWarning);
        assert userWarningDao.getUserWarningByUser(user).get(0) == userWarning;
    }
}