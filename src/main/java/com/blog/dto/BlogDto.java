package com.blog.dto;

import lombok.Data;

@Data
public class BlogDto {
    private int id;
    private String title;
    private String content;
    private int userId;
}
