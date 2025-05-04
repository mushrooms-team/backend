package com.springboot.gangnaenglog_backend.service.impl;

import com.springboot.gangnaenglog_backend.domain.community.Comment;
import com.springboot.gangnaenglog_backend.domain.community.Post;
import com.springboot.gangnaenglog_backend.domain.community.PostLike;
import com.springboot.gangnaenglog_backend.domain.member.Member;
import com.springboot.gangnaenglog_backend.dto.*;
import com.springboot.gangnaenglog_backend.repository.CommentRepository;
import com.springboot.gangnaenglog_backend.repository.MemberRepository;
import com.springboot.gangnaenglog_backend.repository.PostLikeRepository;
import com.springboot.gangnaenglog_backend.repository.PostRepository;
import com.springboot.gangnaenglog_backend.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityServiceImpl implements CommunityService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버 없음"));

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
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

    @Override
    public List<PostListResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostListResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getMember().getNickname(),
                        post.getCreatedAt().toLocalDate().toString()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getMember().getNickname(),
                        comment.getCreatedAt().toString()
                ))
                .toList();
    }

    @Override
    @Transactional
    public CommentResponseDto createComment(Long postId, Long memberId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .post(post)
                .member(member)
                .build();

        Comment saved = commentRepository.save(comment);

        return new CommentResponseDto(
                saved.getId(),
                saved.getContent(),
                member.getNickname(),
                saved.getCreatedAt().toString()
        );
    }

    @Override
    public List<PostListResponseDto> getPopularPosts() {
        LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
        List<Post> posts = postRepository.findPopularPosts(10, twoWeeksAgo);

        return posts.stream()
                .map(post -> new PostListResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getMember().getNickname(),
                        post.getCreatedAt().toLocalDate().toString()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void likePost(Long memberId, Long postId) {
        if (postLikeRepository.existsByMemberIdAndPostId(memberId, postId)) {
            throw new IllegalStateException("이미 좋아요를 누른 게시글입니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        PostLike postLike = PostLike.builder()
                .member(member)
                .post(post)
                .build();

        postLikeRepository.save(postLike);
    }





}