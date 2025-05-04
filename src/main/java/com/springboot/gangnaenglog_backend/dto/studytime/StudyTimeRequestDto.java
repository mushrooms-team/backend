package com.springboot.gangnaenglog_backend.dto.studytime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudyTimeRequestDto {
    private LocalDate date;
    private int hours;
    private int minutes;
    private int seconds;

    public LocalTime toLocalTime() {
        return LocalTime.of(hours, minutes, seconds);
    }
}
