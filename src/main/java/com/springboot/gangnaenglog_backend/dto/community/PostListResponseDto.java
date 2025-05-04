package com.springboot.gangnaenglog_backend.dto.community;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private String createdAt;
}
