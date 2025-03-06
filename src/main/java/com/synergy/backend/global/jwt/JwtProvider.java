package com.synergy.backend.global.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 시크릿 키 생성
	private final long expirationMs = 1000 * 60 * 60; // 1시간

	// JWT 생성
	public String generateToken(String username, String role) {
		return Jwts.builder()
			.setSubject(username)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
			.signWith(key)
			.compact();
	}

	// JWT 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// JWT에서 사용자 정보 추출
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}
}
