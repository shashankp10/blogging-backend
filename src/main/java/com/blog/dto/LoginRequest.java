package com.blog.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private int id;
    private String email;
    private String password;
}
