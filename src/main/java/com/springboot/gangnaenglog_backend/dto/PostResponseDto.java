package com.springboot.gangnaenglog_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;

    private String title;

    private String content;

    private String nickname;

    private String createdAt;
}
