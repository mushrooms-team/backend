package com.springboot.gangnaenglog_backend.dto.studytime;

import com.springboot.gangnaenglog_backend.domain.studytime.StudyTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudyTimeResponseDto {
    private Long id;
    private LocalDate date;
    private int hours;
    private int minutes;
    private int seconds;
    private String nickname;

    public static StudyTimeResponseDto fromEntity(StudyTime studyTime) {
        return new StudyTimeResponseDto(
                studyTime.getId(),
                studyTime.getDate(),
                studyTime.getTime().getHour(),
                studyTime.getTime().getMinute(),
                studyTime.getTime().getSecond(),
                studyTime.getMember().getNickname()
        );
    }
}
