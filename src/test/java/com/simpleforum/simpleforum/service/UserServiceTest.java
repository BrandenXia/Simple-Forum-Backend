package com.simpleforum.simpleforum.service;

import com.simpleforum.simpleforum.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        userService.createUser("test", "test", "test@test.com", "123456789");
        assert userService.getUserByUsername("test") != null;
    }

    @Test
    void deleteUser() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        userService.deleteUser(user.getID());
        assert userService.getUserByID(user.getID()) == null;
    }

    @Test
    void updateUser() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        userService.updateUser(user.getID(), "test2", "test2", "test2@test.com", "987654321");
        assert userService.getUserByID(user.getID()).getUsername().equals("test2");
    }

    @Test
    void getUserByID() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userService.getUserByID(user.getID());
        assert user2.equals(user);
    }

    @Test
    void getUserByUsername() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userService.getUserByUsername(user.getUsername());
        assert user2.equals(user);
    }

    @Test
    void getUserByEmail() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userService.getUserByEmail(user.getEmail());
        assert user2.equals(user);
    }

    @Test
    void getUserByPhoneNumber() {
        User user = userService.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userService.getUserByPhoneNumber(user.getPhoneNumber());
        assert user2.equals(user);
    }
}