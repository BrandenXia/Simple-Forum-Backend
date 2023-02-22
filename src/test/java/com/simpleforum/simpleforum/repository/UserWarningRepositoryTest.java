package com.simpleforum.simpleforum.repository;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.simpleforum.simpleforum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
@Transactional
class UserWarningRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWarningRepository userWarningRepository;

    @Test
    void addUserWarning() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.createUser(user);
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningRepository.addUserWarning(user, userWarning);
        assert userWarningRepository.getUserWarningById(userWarning.getID()) == userWarning;
    }

    @Test
    void deleteUserWarning() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.createUser(user);
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningRepository.addUserWarning(user, userWarning);
        userWarningRepository.deleteUserWarning(user, userWarning);
        assert userWarningRepository.getUserWarningById(userWarning.getID()) == null;
    }

    @Test
    void getUserWarningById() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.createUser(user);
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningRepository.addUserWarning(user, userWarning);
        assert userWarningRepository.getUserWarningById(userWarning.getID()) == userWarning;
    }

    @Test
    void getUserWarningByUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.createUser(user);
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningRepository.addUserWarning(user, userWarning);
        assert userWarningRepository.getUserWarningByUser(user, 1).get(0) == userWarning;
    }

    @Test
    void GetUserWarningByUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepository.createUser(user);
        UserWarning userWarning = new UserWarning();
        userWarning.setUser(user);
        userWarning.setReason("test");
        userWarning.setDate(new Timestamp(System.currentTimeMillis()));
        userWarning.setID(NanoIdUtils.randomNanoId());
        userWarning.setModeratorID(user.getID());
        userWarningRepository.addUserWarning(user, userWarning);
        assert userWarningRepository.getUserWarningByUser(user).get(0) == userWarning;
    }
}