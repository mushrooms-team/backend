package com.springboot.gangnaenglog_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_study_time")
public class UserStudyTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ✔️ 내부 식별용 PK

    private String nickname;

    private int todayStudyTime = 0;
    private int weeklyStudyTime = 0;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // ✔️ FK 필드 명시
    private User user;

    public UserStudyTime(User user, String nickname, int today, int weekly, LocalDateTime updatedAt) {
        this.user = user; // ✔️ FK 설정
        this.nickname = nickname;
        this.todayStudyTime = today;
        this.weeklyStudyTime = weekly;
        this.updatedAt = updatedAt;
    }

    public void updateStudyTimes(int today, int weekly) {
        this.todayStudyTime = today;
        this.weeklyStudyTime = weekly;
        this.updatedAt = LocalDateTime.now();
    }
}
