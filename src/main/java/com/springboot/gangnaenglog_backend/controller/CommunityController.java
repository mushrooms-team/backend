package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.*;
import com.springboot.gangnaenglog_backend.service.CommunityService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        //인증된 사용자 정보에서 memberId 추출
        Long memberId = 1L; // 임시
        PostResponseDto responseDto = communityService.createPost(requestDto, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostListResponseDto>> getAllPosts() {
        return ResponseEntity.ok(communityService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto post = communityService.getPostDetail(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(@PathVariable Long id) {
        List<CommentResponseDto> comments = communityService.getCommentsByPostId(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto
    ) {
        Long memberId = 1L; // 임시
        CommentResponseDto responseDto = communityService.createComment(id, memberId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }




}
