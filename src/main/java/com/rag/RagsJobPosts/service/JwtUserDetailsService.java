package com.rag.RagsJobPosts.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.models.UserEntity;

@Service
public class JwtUserDetailsService {

	public UserEntity loadUserByEmail(String email) {

		if ("rag@gmail.com".equals(email)) {
			UserEntity user = new UserEntity();
			user.setCompanyName("abc");
			user.setUsername("ragbag");
			user.setEmail("abc@gmail.com");
			user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
			return user;

		}
		return null;
	}

}
