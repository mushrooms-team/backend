package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.dto.*;

import java.util.List;

public interface CommunityService {
    PostResponseDto createPost(PostRequestDto requestDto, Long memberId);

    List<PostResponseDto> getPostList();

    PostResponseDto getPostDetail(Long postId);

    List<PostListResponseDto> getAllPosts();

    List<CommentResponseDto> getCommentsByPostId(Long postId);

    CommentResponseDto createComment(Long postId, Long memberId, CommentRequestDto requestDto);

}
