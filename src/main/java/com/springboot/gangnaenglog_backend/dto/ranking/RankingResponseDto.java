package com.springboot.gangnaenglog_backend.dto.ranking;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponseDto {
    private String nickname;
    private int rank;
    private int studyTime;
}
