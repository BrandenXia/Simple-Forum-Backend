package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserWarningRepository extends JpaRepository<UserWarning, String> {
    Page<UserWarning> findByUser(User user, Pageable pageable);

    Page<UserWarning> findByModerator(User moderator, Pageable pageable);

    Page<UserWarning> findByDateBetween(Date date1, Date date2, Pageable pageable);

    Page<UserWarning> findByDateAfter(Date date, Pageable pageable);

    Page<UserWarning> findByDateBefore(Date date, Pageable pageable);
}
