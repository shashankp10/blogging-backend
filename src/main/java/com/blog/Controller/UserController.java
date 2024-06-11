package com.blog.Controller;

import com.blog.Security.JWTTokenUtil;
import com.blog.Service.UserService;
import com.blog.DTO.JWTResponse;
import com.blog.DTO.LoginRequest;
import com.blog.DTO.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody UserDto registrationDto) {
        userService.createUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
            return ResponseEntity.ok(new JWTResponse(jwt));
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Invalid email or password") {};
        }
    }
}

/*
    Tech-Stack used :
        - Springboot
        - MySQL
        - JWT of springboot 3.0 Version

    End-points :
        Register a new user : http://localhost:8080/api/v1/register/user  [POST Request]
        Login as user : http://localhost:8080/api/v1/login         [POST Request]

    Secured End-points required JWT token to access
        Crate a new blog : http://localhost:8080/api/v1/blog/post  [POST Request]
        Get all blog : http://localhost:8080/api/v1/blog/       [GET Request]
        Get all blog a userId : http://localhost:8080/api/v1/blog/user/{blogId}     [GET Request]
        Get blog by Id : http://localhost:8080/api/v1/blog/{blogId}             [GET Request]
        Update blog by Id : http://localhost:8080/api/v1/blog/{blogId}          [PUT Request]
        Delete blog by Id : http://localhost:8080/api/v1/blog/{blogId}          [DELETE Request]

 */
