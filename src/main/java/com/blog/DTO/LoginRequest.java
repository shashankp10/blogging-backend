package com.blog.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private int id;
    private String email;
    private String password;
}
