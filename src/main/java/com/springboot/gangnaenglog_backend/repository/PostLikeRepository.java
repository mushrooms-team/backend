package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.domain.community.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
