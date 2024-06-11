package com.blog.Service;

import com.blog.DTO.UserDto;

public interface UserService {
    UserDto createUser(UserDto customerDto);
    UserDto updateUser(UserDto customer, int userId);
    UserDto getUserById(int userId);
    void deleteUser(int userId);
    boolean authenticateUser(String email, String password);
}
