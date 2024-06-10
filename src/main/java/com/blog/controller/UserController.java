package com.blog.controller;

import com.blog.Security.JWTTokenUtil;
import com.blog.Service.UserService;
import com.blog.dto.JWTResponse;
import com.blog.dto.LoginRequest;
import com.blog.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
//@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerCustomer(@RequestBody UserDto registrationDto) {
        userService.createUser(registrationDto);
        return ResponseEntity.ok("Customer registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(new JWTResponse(jwt));
    }
}
