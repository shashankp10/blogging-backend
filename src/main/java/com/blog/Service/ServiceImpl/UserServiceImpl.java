package com.blog.Service.ServiceImpl;

import com.blog.Repo.UserRepo;
import com.blog.Service.UserService;
import com.blog.dto.UserDto;
import com.blog.entity.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        UserAuth user = new UserAuth();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserAuth savedUser = userRepo.save(user);
        return convertUserToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        UserAuth user = optionalUser.get();
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserAuth updatedUser = userRepo.save(user);

        return convertUserToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(int userId) {
        Optional<UserAuth> optionalUser = userRepo.findById(userId);
        if (!optionalUser.isPresent()) {
            return null;
        }

        return convertUserToDto(optionalUser.get());
    }

    @Override
    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<UserAuth> optionalUser = userRepo.findByEmail(email);
        if (!optionalUser.isPresent()) {
            return false; // User not found
        }

        UserAuth user = optionalUser.get();
        return passwordEncoder.matches(password, user.getPassword());
    }

    private UserDto convertUserToDto(UserAuth user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
