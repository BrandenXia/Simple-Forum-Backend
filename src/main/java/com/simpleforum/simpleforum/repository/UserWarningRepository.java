package com.simpleforum.simpleforum.repository;

import com.simpleforum.simpleforum.entity.User;
import com.simpleforum.simpleforum.entity.UserWarning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserWarningRepository extends JpaRepository<UserWarning, String> {
    List<UserWarning> findByUser(User user);

    List<UserWarning> findByModerator(User moderator);

    List<UserWarning> findByDateBetween(Date date1, Date date2);

    List<UserWarning> findByDateAfter(Date date);

    List<UserWarning> findByDateBefore(Date date);
}
