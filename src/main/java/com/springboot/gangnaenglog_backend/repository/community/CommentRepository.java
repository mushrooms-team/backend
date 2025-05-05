package com.springboot.gangnaenglog_backend.repository.community;

import com.springboot.gangnaenglog_backend.domain.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

}
