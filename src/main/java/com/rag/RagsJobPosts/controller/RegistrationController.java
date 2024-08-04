package com.rag.RagsJobPosts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
//    @PostMapping(value ="/req/signup", consumes = "application/json")
//    public UserEntity postMethodName(@RequestBody @Valid  UserEntity entity) {
//    	entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//        return userRepository.save(entity);
//    }
    
}
