package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.dto.*;
import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.EmployerMapper;
import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.repository.CompanyRepository;
import com.rag.RagsJobPosts.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.repository.UserRepository;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    @Lazy
    private final CompanyRepository companyRepository;
    @Lazy
    private final EmployerRepository employerRepository;

    private final EmployerMapper employerMapper;
//    private final JobPoster companyRepository;




    public UserEntity signup(RegisterUserDto input) {
        UserEntity user = new UserEntity();
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                user.setUsername(input.getUsername());
                user.setEmail(input.getEmail());

        return userRepository.save(user);
    }

    public EmployerRegisterResponseDTO registerEmployer(RegisterEmployerDto employerDto) {
        UserEntity user = new UserEntity();
        Long companyId = employerDto.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(()->{
            log.error("Company is not found for id {}", companyId);
            return new ResourceNotFoundException("Company is not found for id "+companyId);
        });

        user.setPassword(passwordEncoder.encode(employerDto.getPassword()));
        user.setUsername(employerDto.getUsername());
        user.setEmail(employerDto.getEmail());
        user.setRoles(List.of("USER","JOB_POSTER"));
        UserEntity userEntity = userRepository.save(user);

        JobPoster jobPoster = new JobPoster();
        jobPoster.setCompany(company);
        jobPoster.setUser(userEntity);
        jobPoster.setVerifiedByCompany(false);
        employerRepository.save(jobPoster);
        return employerMapper.entityToRegisterResponseDTO(jobPoster);
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
