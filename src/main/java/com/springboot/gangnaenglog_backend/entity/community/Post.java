package com.springboot.gangnaenglog_backend.entity.community;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

}
