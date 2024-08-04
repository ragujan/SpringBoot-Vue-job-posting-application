package com.rag.RagsJobPosts.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = repository.findByUsername(username);
		System.out.println("came here came here ");
		System.out.println("came here came here ");
		System.out.println("came here came here ");
		System.out.println("came here came here 333 "+username);
		if(user.isPresent()) {
			System.out.println("user found");
			var userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.build();
		}else {
			System.out.println("username is not found");
			throw new UsernameNotFoundException("username not found");
		}
	}

}
