package com.rag.RagsJobPosts.controller;

import java.time.ZonedDateTime;

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
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser.getUsername());
        ZonedDateTime expiryTime = jwtService.getExpirationDateFromToken(jwtToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(expiryTime.toInstant().toEpochMilli());

//        .setToken(jwtToken).setExpiresIn(jwtService.getExpirationDateFromToken(jwtToken))
        return ResponseEntity.ok(loginResponse);
    }
}
