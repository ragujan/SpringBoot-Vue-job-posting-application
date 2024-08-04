package com.rag.RagsJobPosts.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.dto.LoginUserDto;
import com.rag.RagsJobPosts.dto.RegisterUserDto;
import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    public UserEntity signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                user.setCompanyName(input.getCompanyName());
                user.setUsername(input.getUsername());
                user.setEmail(input.getEmail());

        return userRepository.save(user);
    }
    
    public UserEntity authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
