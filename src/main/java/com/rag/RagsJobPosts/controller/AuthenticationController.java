package com.rag.RagsJobPosts.controller;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import com.rag.RagsJobPosts.dto.RegisterAdminDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rag.RagsJobPosts.dto.LoginResponse;
import com.rag.RagsJobPosts.dto.LoginUserDto;
import com.rag.RagsJobPosts.dto.RegisterUserDto;
import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.services.AuthenticationService;
import com.rag.RagsJobPosts.services.JwtService;

import io.fusionauth.jwt.JWTUtils;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    @GetMapping("/login")
    public String loginView() {
    	return "login ";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/register-admin")
    public ResponseEntity<UserEntity> registerAdmin(@RequestBody RegisterAdminDto registerAdminDto) {
        UserEntity registeredUser = authenticationService.registerAdmin(registerAdminDto);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login-admin")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse authenticatedUser = authenticationService.authenticateAdmin(loginUserDto);
        return ResponseEntity.ok(authenticatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse authenticatedUser = authenticationService.authenticate(loginUserDto);
        return ResponseEntity.ok(authenticatedUser);
    }
}
