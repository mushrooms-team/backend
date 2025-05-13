package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.dto.ranking.RankingResponseDto;
import com.springboot.gangnaenglog_backend.entity.UserStudyTime;
import com.springboot.gangnaenglog_backend.repository.UserStudyTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {
    private final UserStudyTimeRepository userStudyTimeRepository;

    public List<RankingResponseDto> getTodayTop100Ranking() {
        List<UserStudyTime> users = userStudyTimeRepository.findTop100ByOrderByTodayStudyTimeDesc();

        List<RankingResponseDto> rankings = new ArrayList<>();
        int rank = 1;

        for (UserStudyTime user : users) {
            rankings.add(new RankingResponseDto(
                    user.getNickname(),
                    rank++,
                    user.getTodayStudyTime()
            ));
        }

        return rankings;
    }

    public List<RankingResponseDto> getBiweeklyTop3Ranking() {
        List<UserStudyTime> users = userStudyTimeRepository.findTop3ByOrderByWeeklyStudyTimeDesc();

        List<RankingResponseDto> rankings = new ArrayList<>();
        int rank = 1;

        for (UserStudyTime user : users) {
            rankings.add(new RankingResponseDto(
                    user.getNickname(),
                    rank++,
                    user.getWeeklyStudyTime()
            ));
        }

        return rankings;
    }

}
