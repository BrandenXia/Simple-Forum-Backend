package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User getByUsername(String username);

    User getByEmail(String email);

    User getByPhoneNumber(String phoneNumber);

    User getByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

    List<User> getByRegistrationDateBetween(Date date1, Date date2);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}