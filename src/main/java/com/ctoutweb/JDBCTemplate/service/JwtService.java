package com.ctoutweb.JDBCTemplate.service;

import java.security.Key;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	Dotenv dotenv = Dotenv.load();
	
	private final String SECRET_KEY = dotenv.get("SECRET_TOKEN");
	
	public String extractUserName(String token) {		
		return extractClaim(token, Claims::getSubject);		
	}
	
	public <T> T extractClaim(String token, Function<Claims, T>  claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Key getSignInKey() {
		System.out.println(SECRET_KEY);
		byte[] keyBytes = Decoders.BASE64.decode(dotenv.get("SECRET_TOKEN"));
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
