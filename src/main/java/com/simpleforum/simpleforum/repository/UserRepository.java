package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    User findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

    List<User> findByRegistrationDateBetween(Date date1, Date date2);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}