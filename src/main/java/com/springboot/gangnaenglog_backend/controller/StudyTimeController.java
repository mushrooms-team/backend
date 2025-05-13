package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.ranking.StudyTimeRequestDto;
import com.springboot.gangnaenglog_backend.jwt.CustomUserDetails;
import com.springboot.gangnaenglog_backend.service.StudyTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study-times")
public class StudyTimeController {
    private final StudyTimeService studyTimeService;

    @PostMapping
    public ResponseEntity<Void> saveStudyTime(
            @RequestBody StudyTimeRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        studyTimeService.saveStudyTime(
                userDetails.getId(),
                userDetails.getName(),
                requestDto.getTodayStudyTime(),
                requestDto.getWeeklyStudyTime()
        );
        return ResponseEntity.ok().build();
    }
}
