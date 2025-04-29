package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.domain.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
