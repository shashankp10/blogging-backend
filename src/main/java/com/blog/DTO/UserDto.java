package com.blog.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class UserDto {

    private int id;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace"
    )
    private String password;
}
