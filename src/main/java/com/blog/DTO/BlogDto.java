package com.blog.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BlogDto {
    private int id;
    @NotBlank(message = "Title is mandatory")
    @Size(min = 5, max = 100, message = "Title should be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Content is Empty")
    private String content;

    private int userId;
}
