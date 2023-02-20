package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        userRepository.createUser("test", "test", "test@test.com", "123456789");
        assert userRepository.getUserByUsername("test") != null;
    }

    @Test
    void deleteUser() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        userRepository.deleteUser(user);
        assert userRepository.getUserByID(user.getID()) == null;
    }

    @Test
    void updateUser() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        userRepository.updateUser(user, "test2", "test2", "test2@test.com", "987654321");
        assert userRepository.getUserByID(user.getID()).getUsername().equals("test2");
    }

    @Test
    void getUserByID() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userRepository.getUserByID(user.getID());
        assert user2.equals(user);
    }

    @Test
    void getUserByUsername() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userRepository.getUserByUsername(user.getUsername());
        assert user2.equals(user);
    }

    @Test
    void getUserByEmail() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userRepository.getUserByEmail(user.getEmail());
        assert user2.equals(user);
    }

    @Test
    void getUserByPhoneNumber() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        User user2 = userRepository.getUserByPhoneNumber(user.getPhoneNumber());
        assert user2.equals(user);
    }
}