package com.synergy.backend.global.security;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.synergy.backend.domain.member.entity.RoleType;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
public class JwtProvider {

	private final JwtProperties jwtProperties;
	private SecretKey key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
	}

	public String generateAccessToken(CustomUserDetails userDetails) {
		return generateToken(userDetails, jwtProperties.accessTokenExpiration());
	}

	public String generateRefreshToken(CustomUserDetails userDetails) {
		return generateToken(userDetails, jwtProperties.refreshTokenExpiration());
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public String getEmailOrAuthCodeFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}

	public RoleType getRoleTypeFromToken(String token) {
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

	private String generateToken(CustomUserDetails userDetails, Duration expiration) {

		return Jwts.builder()
			.setSubject(userDetails.getIdentifier())
			.claim("id", userDetails.getId())
			.claim("role", userDetails.getRole().getAuthority())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expiration.toMillis()))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}
}
