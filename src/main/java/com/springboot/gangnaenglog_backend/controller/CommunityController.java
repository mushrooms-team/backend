package com.springboot.gangnaenglog_backend.controller;

import com.springboot.gangnaenglog_backend.dto.community.*;
import com.springboot.gangnaenglog_backend.service.CommunityService;
import com.springboot.gangnaenglog_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class CommunityController {

    private final CommunityService communityService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Long memberId = userService.getUserIdByEmail(email);
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
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Long memberId = userService.getUserIdByEmail(email);
        CommentResponseDto responseDto = communityService.createComment(id, memberId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PostListResponseDto>> getPopularPosts() {
        return ResponseEntity.ok(communityService.getPopularPosts());
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Long memberId = userService.getUserIdByEmail(email);
        communityService.likePost(memberId, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> unLikePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Long memberId = userService.getUserIdByEmail(email);
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
