package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.domain.community.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByMemberIdAndPostId(Long memberId, Long postId);

    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);

    long countByPostId(Long postId);

}

