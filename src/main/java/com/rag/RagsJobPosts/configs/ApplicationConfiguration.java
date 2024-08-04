package com.rag.RagsJobPosts.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rag.RagsJobPosts.repository.UserRepository;
import com.rag.RagsJobPosts.services.UserService;

@Configuration
public class ApplicationConfiguration {

	@Autowired
	private final UserRepository userRepository;
	
	
	public ApplicationConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Bean
	UserDetailsService userDetailsService() {
		return username->userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found "));

	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	@Bean
	 AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}




//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//				.formLogin(httpForm -> {
//					httpForm.loginPage("/login").usernameParameter("username")
//					.passwordParameter("password")
//					.permitAll();
//				})
////				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(registry -> {
//					registry.requestMatchers("req/signup", "/css/**","login").permitAll();
//					registry.anyRequest().authenticated();
//				}).build();
//	}
}
