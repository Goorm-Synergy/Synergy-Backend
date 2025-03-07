package com.synergy.backend.global.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import com.synergy.backend.domain.member.entity.Member;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

	private final Key key;

	private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15; // 15분
	private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7일

	public JwtProvider(@Value("${jwt.secret}") String secret) {
		byte[] decodedKey = Base64.getDecoder().decode(secret);
		this.key = Keys.hmacShaKeyFor(decodedKey);
	}

	public String generateToken(String email, String role, boolean isAccessToken) {
		long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;

		return Jwts.builder()
			.setSubject(email)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public String getEmailFromToken(String token) {
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
