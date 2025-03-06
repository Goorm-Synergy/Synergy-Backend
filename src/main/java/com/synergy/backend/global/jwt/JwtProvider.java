package com.synergy.backend.global.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import com.synergy.backend.domain.member.entity.Member;

@Component
public class JwtProvider {

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 시크릿 키 생성
	private final long expirationMs = 1000 * 60 * 60; // 1시간
	private final String secretKey = "your-secret-key-your-secret-key"; // 반드시 256bit 이상 길이

	// JWT 생성
	public String generateToken(Member member) {
		return Jwts.builder()
			.setSubject(member.getEmail())
			.claim("role", member.getClass().getSimpleName())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
			.signWith(key, SignatureAlgorithm.HS256)
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

	public String getRoleFromToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.get("role", String.class);
	}
}
