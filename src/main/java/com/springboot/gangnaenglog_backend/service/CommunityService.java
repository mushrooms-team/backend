package com.springboot.gangnaenglog_backend.service;

import com.springboot.gangnaenglog_backend.dto.PostRequestDto;
import com.springboot.gangnaenglog_backend.dto.PostResponseDto;

import java.util.List;

public interface CommunityService {
    PostResponseDto createPost(PostRequestDto requestDto, Long memberId);

    List<PostResponseDto> getPostList();

    PostResponseDto getPostDetail(Long postId);
}
