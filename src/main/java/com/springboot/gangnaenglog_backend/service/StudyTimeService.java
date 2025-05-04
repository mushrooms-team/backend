package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.dto.studytime.StudyTimeRequestDto;
import com.springboot.gangnaenglog_backend.dto.studytime.StudyTimeResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface StudyTimeService {
    StudyTimeResponseDto saveStudyTime(Long memberId, StudyTimeRequestDto requestDto);
    List<StudyTimeResponseDto> getStudyTimesByDate(LocalDate date);
}
