package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.dto.LoginResponse;
import com.rag.RagsJobPosts.dto.RegisterAdminDto;
import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.dto.LoginUserDto;
import com.rag.RagsJobPosts.dto.RegisterUserDto;
import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.repository.UserRepository;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    
    public UserEntity signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                user.setUsername(input.getUsername());
                user.setEmail(input.getEmail());

        return userRepository.save(user);
    }

    public UserEntity registerAdmin(RegisterAdminDto adminDto) {
        UserEntity user = new UserEntity();
        user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        user.setUsername(adminDto.getUsername());
        user.setEmail(adminDto.getEmail());
        user.setRoles(List.of("ADMIN","USER","JOB_POSTER","JOB_SEEKER"));
        return userRepository.save(user);
    }
    public LoginResponse authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        UserEntity entity = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        List<String> roles = new LinkedList<>();
        roles.add("user");
        String jwtToken = jwtService.generateToken(entity.getUsername(),roles);
        ZonedDateTime expiryTime = jwtService.getExpirationDateFromToken(jwtToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(expiryTime.toInstant().toEpochMilli());


        return loginResponse;
    }

    public LoginResponse authenticateAdmin(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        UserEntity entity = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user is not found for this"));
        List<String>roles = entity.getRoles();
        String jwtToken = jwtService.generateToken(entity.getUsername(),roles);
        ZonedDateTime expiryTime = jwtService.getExpirationDateFromToken(jwtToken);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        long minutes = Duration.between(ZonedDateTime.now(), expiryTime).toMinutes();
        loginResponse.setExpiresIn(minutes);

        return loginResponse;
    }
}
