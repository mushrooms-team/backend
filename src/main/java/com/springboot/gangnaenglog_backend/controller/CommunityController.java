package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.community.*;
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
        Long memberId = 1L;
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
        Long memberId = 1L;
        CommentResponseDto responseDto = communityService.createComment(id, memberId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PostListResponseDto>> getPopularPosts() {
        return ResponseEntity.ok(communityService.getPopularPosts());
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id) {
        Long memberId = 1L;
        communityService.likePost(memberId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> unLikePost(
            @PathVariable Long id,
            @RequestParam Long memberId
    ) {
        communityService.unLikePost(memberId, id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/like")
    public ResponseEntity<Long> getLikesCount(@PathVariable("id") Long postId) {
        long count = communityService.getPostLikesCount(postId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostListResponseDto>> searchPosts(@RequestParam String keyword) {
        return ResponseEntity.ok(communityService.searchPosts(keyword));
    }


}
