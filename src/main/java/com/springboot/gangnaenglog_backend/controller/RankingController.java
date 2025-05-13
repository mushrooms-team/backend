package com.springboot.gangnaenglog_backend.controller;


import com.springboot.gangnaenglog_backend.dto.ranking.RankingResponseDto;
import com.springboot.gangnaenglog_backend.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<RankingResponseDto>> getRankings(
            @RequestParam(name = "range", defaultValue = "day") String range
    ) {
        List<RankingResponseDto> rankings;

        switch (range) {
            case "day":
                rankings = rankingService.getTodayTop100Ranking();
                break;
            case "week":
                rankings = rankingService.getBiweeklyTop3Ranking();
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 랭킹 범위입니다: " + range);
        }

        return ResponseEntity.ok(rankings);
    }

}
