package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    Pageable pageable = PageRequest.of(0, 10);

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
        User user = userRepository.findByUsername("test");
        Page<UserWarning> userWarnings = userWarningRepository.findByUser(user, pageable);
        UserWarning userWarning = userWarnings.getContent().get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        User moderator = userRepository.findByUsername("moderator");
        assertEquals(0, userWarningRepository.findByUser(moderator, pageable).getContent().size());
    }

    @Test
    void findByModerator() {
        User moderator = userRepository.findByUsername("moderator");
        Page<UserWarning> userWarnings = userWarningRepository.findByModerator(moderator, pageable);
        UserWarning userWarning = userWarnings.getContent().get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        User user = userRepository.findByUsername("test");
        assertEquals(0, userWarningRepository.findByModerator(user, pageable).getContent().size());
    }

    @Test
    void findByDateBetween() {
        Page<UserWarning> userWarnings = userWarningRepository.findByDateBetween(new Date(0), new Date(), pageable);
        UserWarning userWarning = userWarnings.getContent().get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        assertEquals(0, userWarningRepository.findByDateBetween(new Date(0), new Date(0), pageable).getContent().size());
    }

    @Test
    void findByDateAfter() {
        Page<UserWarning> userWarnings = userWarningRepository.findByDateAfter(new Date(0), pageable);
        UserWarning userWarning = userWarnings.getContent().get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        assertEquals(0, userWarningRepository.findByDateAfter(new Date(), pageable).getContent().size());
    }

    @Test
    void findByDateBefore() {
        Page<UserWarning> userWarnings = userWarningRepository.findByDateBefore(new Date(), pageable);
        UserWarning userWarning = userWarnings.getContent().get(0);
        assertNotNull(userWarning);
        assertEquals("test", userWarning.getId());
        assertEquals(0, userWarningRepository.findByDateBefore(new Date(0), pageable).getContent().size());
    }
}