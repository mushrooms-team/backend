package com.springboot.gangnaenglog_backend.repository;

import com.springboot.gangnaenglog_backend.domain.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.createdAt >= :startDate AND SIZE(p.likes) >= :minLikes ORDER BY SIZE(p.likes) DESC")
    List<Post> findPopularPosts(
            @Param("minLikes") int minLikes,
            @Param("startDate") LocalDateTime startDate);

    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String titleKeyword, String contentKeyword);

}
