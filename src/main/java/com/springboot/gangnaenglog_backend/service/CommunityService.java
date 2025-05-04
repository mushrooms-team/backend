package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.dto.*;

import java.util.List;

public interface CommunityService {
    PostResponseDto createPost(PostRequestDto requestDto, Long memberId);

    PostResponseDto getPostDetail(Long postId);

    List<PostListResponseDto> getAllPosts();

    List<CommentResponseDto> getCommentsByPostId(Long postId);

    CommentResponseDto createComment(Long postId, Long memberId, CommentRequestDto requestDto);

    List<PostListResponseDto> getPopularPosts();

    void likePost(Long memberId, Long postId);

    void unLikePost(Long memberId, Long postId);

    long getPostLikesCount(Long postId);

    List<PostListResponseDto> searchPosts(String keyword);


}
