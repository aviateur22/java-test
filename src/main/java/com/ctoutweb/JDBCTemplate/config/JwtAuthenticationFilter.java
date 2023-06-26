package com.ctoutweb.JDBCTemplate.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ctoutweb.JDBCTemplate.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	private final JwtService jwtService;

	public JwtAuthenticationFilter(JwtService jwtService) {
		super();
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain .doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		String userEmail = jwtService.extractUserName(jwt);
	
		
	}

}
