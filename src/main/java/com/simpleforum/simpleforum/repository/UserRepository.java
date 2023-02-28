package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);

    User findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);

    Page<User> findByRegistrationDateBetween(Date date1, Date date2, Pageable pageable);

    Page<User> findByRegistrationDateAfter(Date date, Pageable pageable);

    Page<User> findByRegistrationDateBefore(Date date, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);
}