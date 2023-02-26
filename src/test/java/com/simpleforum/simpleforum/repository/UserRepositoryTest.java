package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setID("test");
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setPhoneNumber("1234567890");
        user.setLastLoginDate(new Date());
        user.setRegistrationDate(new Date());
        userRepository.save(user);
    }

    @Test
    void findByUsername() {
        User user = userRepository.findByUsername("test");
        assertNotNull(user);
        assertEquals("test", user.getID());
        User user1 = userRepository.findByUsername("test1");
        assertNull(user1);
    }

    @Test
    void findByEmail() {
        User user = userRepository.findByEmail("test@test.com");
        assertNotNull(user);
        assertEquals("test", user.getID());
        User user1 = userRepository.findByEmail("test1@test.com");
        assertNull(user1);
    }

    @Test
    void findByPhoneNumber() {
        User user = userRepository.findByPhoneNumber("1234567890");
        assertNotNull(user);
        assertEquals("test", user.getID());
        User user1 = userRepository.findByPhoneNumber("1234567891");
        assertNull(user1);
    }

    @Test
    void findByUsernameOrEmailOrPhoneNumber() {
        User user = userRepository.findByUsernameOrEmailOrPhoneNumber("test", null, null);
        assertNotNull(user);
        assertEquals("test", user.getID());
        User user1 = userRepository.findByUsernameOrEmailOrPhoneNumber(null, "test@test.com", null);
        assertNotNull(user1);
        assertEquals("test", user1.getID());
        User user2 = userRepository.findByUsernameOrEmailOrPhoneNumber(null, null, "1234567890");
        assertNotNull(user2);
        assertEquals("test", user2.getID());
        User user3 = userRepository.findByUsernameOrEmailOrPhoneNumber("test1", "test1@test.com", "1234567891");
        assertNull(user3);
    }

    @Test
    void findByRegistrationDateBetween() {
        User user = userRepository.findByRegistrationDateBetween(new Date(0), new Date()).get(0);
        assertNotNull(user);
        assertEquals("test", user.getID());
        assertEquals(0, userRepository.findByRegistrationDateBetween(new Date(0), new Date(0)).size());
    }

    @Test
    void existsByUsername() {
        assertTrue(userRepository.existsByUsername("test"));
        assertFalse(userRepository.existsByUsername("test1"));
    }

    @Test
    void existsByEmail() {
        assertTrue(userRepository.existsByEmail("test@test.com"));
        assertFalse(userRepository.existsByEmail("test1@test.com"));
    }

    @Test
    void existsByPhoneNumber() {
        assertTrue(userRepository.existsByPhoneNumber("1234567890"));
        assertFalse(userRepository.existsByPhoneNumber("1234567891"));
    }
}