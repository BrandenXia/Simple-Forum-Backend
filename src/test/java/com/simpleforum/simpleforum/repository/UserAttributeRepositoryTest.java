package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserAttribute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Transactional
class UserAttributeRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAttributeRepository userAttributeRepository;

    @Test
    void setUserAttribute() {
        User user = userRepository.createUser("test", "test", "test@test.com", "123456789");
        UserAttribute userAttribute = new UserAttribute();
        userAttribute.setUser(user);
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("test", "test");
        userAttribute.setAttributes(attribute);
        userAttributeRepository.setUserAttribute(user, userAttribute);
        assert user.getUserAttribute().equals(userAttribute);
    }
}