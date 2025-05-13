package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.entity.User;
import com.springboot.gangnaenglog_backend.entity.UserStudyTime;
import com.springboot.gangnaenglog_backend.repository.UserRepository;
import com.springboot.gangnaenglog_backend.repository.UserStudyTimeRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudyTimeService {
    private final UserStudyTimeRepository userStudyTimeRepository;
    private final UserRepository userRepository;

    public void saveStudyTime(Long userId, String nickname, int today, int weekly) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserStudyTime userTime = userStudyTimeRepository.findByUser(user)
                .orElse(new UserStudyTime(user, nickname, 0, 0, LocalDateTime.now()));

        userTime.updateStudyTimes(today, weekly);
        userStudyTimeRepository.save(userTime);
    }

}
