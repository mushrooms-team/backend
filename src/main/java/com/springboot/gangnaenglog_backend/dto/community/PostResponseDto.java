package com.springboot.gangnaenglog_backend.dto.community;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;

    private String title;

    private String content;

    private String nickname;

    private String createdAt;
}
