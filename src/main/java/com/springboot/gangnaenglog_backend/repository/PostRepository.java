package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.domain.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
