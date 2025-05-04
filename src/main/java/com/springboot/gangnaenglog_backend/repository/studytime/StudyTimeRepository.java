package com.springboot.gangnaenglog_backend.repository.studytime;

import com.springboot.gangnaenglog_backend.domain.studytime.StudyTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Long> {
    List<StudyTime> findByMemberIdAndDate(Long memberId, LocalDate date);
    List<StudyTime> findByDate(LocalDate date);
}
