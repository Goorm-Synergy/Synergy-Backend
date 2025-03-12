package com.synergy.backend.global.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.synergy.backend.domain.member.api.dto.resposne.TokenResponseDto;
import com.synergy.backend.domain.member.entity.RoleType;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

	private final Key key;
	private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15; // 15분
	private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7일
	public JwtProvider(@Value("${jwt.secret}") String secretKey) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public TokenResponseDto generateToken(CustomUserDetails userDetails) {
		long expiration = ACCESS_TOKEN_EXPIRATION;

		String token = Jwts.builder()
			.setSubject(userDetails.getUsername())
			.claim("id", userDetails.getId())
			.claim("role", userDetails.getRole().getAuthority())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return new TokenResponseDto(token, userDetails.getUsername(), userDetails.getRole().getAuthority());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public String getEmailOrAuthCodeFromToken(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public RoleType getRoleFromToken(String token) {
		String role = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.get("role", String.class);

		if (role != null && role.startsWith("ROLE_")) {
			role = role.substring(5);
		}

		return RoleType.valueOf(role);
	}
}
