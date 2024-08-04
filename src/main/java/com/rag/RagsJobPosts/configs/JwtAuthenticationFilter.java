package com.rag.RagsJobPosts.configs;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.rag.RagsJobPosts.services.JwtService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	private final HandlerExceptionResolver handlerExceptionResolver;
	
    private final UserDetailsService userDetailsService;
    private final JwtService jwtTokenUtil;
    
    public JwtAuthenticationFilter(
            JwtService jwtTokenUtil,
            UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver
        ) {
            this.jwtTokenUtil =jwtTokenUtil;
            this.userDetailsService = userDetailsService;
            this.handlerExceptionResolver = handlerExceptionResolver;
        }

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
			final String jwt = authHeader.substring(7);
			final String username = jwtTokenUtil.getUsernameFromToken(jwt);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(username !=null && authentication == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				
				if(jwtTokenUtil.validateToken(jwt, userDetails.getUsername())) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							
							userDetails, null, userDetails.getAuthorities());
					

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}

			filterChain.doFilter(request, response);
		} catch (Exception exception) {
			// TODO: handle exception
			handlerExceptionResolver.resolveException(request, response, null, exception);
		}
		// TODO Auto-generated method stub
		
	}

}
