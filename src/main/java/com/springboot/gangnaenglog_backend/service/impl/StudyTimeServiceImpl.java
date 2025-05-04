package com.springboot.gangnaenglog_backend.service.impl;

import com.springboot.gangnaenglog_backend.domain.member.Member;
import com.springboot.gangnaenglog_backend.domain.studytime.StudyTime;
import com.springboot.gangnaenglog_backend.dto.studytime.StudyTimeRequestDto;
import com.springboot.gangnaenglog_backend.dto.studytime.StudyTimeResponseDto;
import com.springboot.gangnaenglog_backend.repository.community.MemberRepository;
import com.springboot.gangnaenglog_backend.repository.studytime.StudyTimeRepository;
import com.springboot.gangnaenglog_backend.service.StudyTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyTimeServiceImpl implements StudyTimeService {
    private final StudyTimeRepository studyTimeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public StudyTimeResponseDto saveStudyTime(Long memberId, StudyTimeRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        StudyTime studyTime = StudyTime.builder()
                .date(requestDto.getDate())
                .time(requestDto.toLocalTime())
                .member(member)
                .build();

        StudyTime saved = studyTimeRepository.save(studyTime);
        return StudyTimeResponseDto.fromEntity(saved);
    }

    @Override
    public List<StudyTimeResponseDto> getStudyTimesByDate(LocalDate date) {
        return studyTimeRepository.findByDate(date).stream()
                .map(StudyTimeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

}
