package com.rag.RagsJobPosts.controller;

import com.rag.RagsJobPosts.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.services.AuthenticationService;
import com.rag.RagsJobPosts.services.JwtService;

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
    @PostMapping("/register-employer")
    public ResponseEntity<EmployerRegisterResponseDTO> registerEmployer(@RequestBody RegisterEmployerDto registerEmployerDto) {
        EmployerRegisterResponseDTO registerResponseDTO = authenticationService.registerEmployer(registerEmployerDto);
        return ResponseEntity.ok(registerResponseDTO);
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
