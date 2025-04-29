package com.springboot.gangnaenglog_backend.service.impl;

import com.springboot.gangnaenglog_backend.domain.community.Post;
import com.springboot.gangnaenglog_backend.dto.PostRequestDto;
import com.springboot.gangnaenglog_backend.dto.PostResponseDto;
import com.springboot.gangnaenglog_backend.repository.PostRepository;
import com.springboot.gangnaenglog_backend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, Long memberId) {
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                // .memberId(memberId)  // Post 엔티티에 memberId가 있을 경우 추가
                .createdAt(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);

        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                "닉네임임시", // 일단 더미 데이터.. 나중에 Member에서 가져오면 됨
                savedPost.getCreatedAt().toString()
        );
    }

    @Override
    public List<PostResponseDto> getPostList() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        "닉네임임시",
                        post.getCreatedAt().toString()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                "닉네임임시",
                post.getCreatedAt().toString()
        );

    }
}