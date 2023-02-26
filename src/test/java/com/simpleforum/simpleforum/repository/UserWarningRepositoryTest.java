package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserWarningRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWarningRepository userWarningRepository;

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
        User moderator = new User();
        moderator.setID("moderator");
        moderator.setUsername("moderator");
        moderator.setPassword("moderator");
        moderator.setEmail("moderator@moderator.com");
        moderator.setPhoneNumber("123456789");
        moderator.setLastLoginDate(new Date());
        moderator.setRegistrationDate(new Date());
        userRepository.save(user);
        userRepository.save(moderator);
        UserWarning userWarning = new UserWarning();
        userWarning.setId("test");
        userWarning.setUser(user);
        userWarning.setModerator(moderator);
        userWarning.setDescription("test");
        userWarning.setDate(new Date());
        userWarningRepository.save(userWarning);
    }

    @Test
    void findByUser() {
        User user = userRepository.getByUsername("test");
        UserWarning userWarning = userWarningRepository.findByUser(user).get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        User moderator = userRepository.getByUsername("moderator");
        assertEquals(0, userWarningRepository.findByUser(moderator).size());
    }

    @Test
    void findByModerator() {
        User moderator = userRepository.getByUsername("moderator");
        UserWarning userWarning = userWarningRepository.findByModerator(moderator).get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        User user = userRepository.getByUsername("test");
        assertEquals(0, userWarningRepository.findByModerator(user).size());
    }

    @Test
    void findByDateBetween() {
        UserWarning userWarning = userWarningRepository.findByDateBetween(new Date(0), new Date()).get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        assertEquals(0, userWarningRepository.findByDateBetween(new Date(0), new Date(0)).size());
    }
}