package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.entity.User;
import com.springboot.gangnaenglog_backend.entity.UserStudyTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStudyTimeRepository extends JpaRepository<UserStudyTime, Long> {
    List<UserStudyTime> findTop100ByOrderByTodayStudyTimeDesc();

    List<UserStudyTime> findTop3ByOrderByWeeklyStudyTimeDesc();

    Optional<UserStudyTime> findByUser(User user);

}
