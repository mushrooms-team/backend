package com.springboot.gangnaenglog_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String nickname;
    private String createdAt;
}
