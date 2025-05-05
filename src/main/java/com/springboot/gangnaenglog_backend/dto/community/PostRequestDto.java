package com.springboot.gangnaenglog_backend.dto.community;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {
    private String title;

    private String content;
}
