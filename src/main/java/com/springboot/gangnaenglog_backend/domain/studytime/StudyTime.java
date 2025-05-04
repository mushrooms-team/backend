package com.springboot.gangnaenglog_backend.domain.studytime;

import com.springboot.gangnaenglog_backend.domain.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudyTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "study_date", nullable = false)
    private LocalDate studyDate;

    @Column(name = "duration_in_seconds", nullable = false)
    private Long durationInSeconds;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
