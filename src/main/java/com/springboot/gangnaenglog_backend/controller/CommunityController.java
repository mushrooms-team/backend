package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.PostRequestDto;
import com.springboot.gangnaenglog_backend.dto.PostResponseDto;
import com.springboot.gangnaenglog_backend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        // TODO: 인증된 사용자 정보에서 memberId 추출
        Long memberId = 1L; // 임시
        PostResponseDto responseDto = communityService.createPost(requestDto, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
