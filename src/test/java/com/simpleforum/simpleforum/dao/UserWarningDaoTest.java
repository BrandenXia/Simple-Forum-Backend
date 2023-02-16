package com.simpleforum.simpleforum.dao;

import com.simpleforum.simpleforum.domain.User;
import com.simpleforum.simpleforum.domain.UserWarning;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserWarningDaoTest {
    @Autowired
    UserDao userDao;

    @Autowired
    UserWarningDao userWarningDao;

    @Test
    void createWarning() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User moderator = userDao.createUser("test1", "test1", "test1@test.com", "0123456789");
        UserWarning warning = userWarningDao.createWarning(user.getID(), "test", moderator.getID());
        assert userWarningDao.getWarningByID(warning.getID()) != null;
    }

    @Test
    void deleteWarning() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User moderator = userDao.createUser("test1", "test1", "test1@test.com", "0123456789");
        UserWarning warning = userWarningDao.createWarning(user.getID(), "test", moderator.getID());
        assert userWarningDao.deleteWarning(warning.getID());
        assert userWarningDao.getWarningByID(warning.getID()) == null;
    }

    @Test
    void updateWarning() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User moderator = userDao.createUser("test1", "test1", "test1@test.com", "0123456789");
        UserWarning warning = userWarningDao.createWarning(user.getID(), "test", moderator.getID());
        assert userWarningDao.updateWarning(warning.getID(), "test1", null, null);
        assert userWarningDao.getWarningByID(warning.getID()).getReason().equals("test1");
    }

    @Test
    void getWarningByID() {
        User user = userDao.createUser("test", "test", "test@test.com", "123456789");
        User moderator = userDao.createUser("test1", "test1", "test1@test.com", "0123456789");
        UserWarning warning = userWarningDao.createWarning(user.getID(), "test", moderator.getID());
        assert userWarningDao.getWarningByID(warning.getID()) != null;
    }
}