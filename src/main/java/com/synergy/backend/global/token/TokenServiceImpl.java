package com.synergy.backend.global.token;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.synergy.backend.global.jwt.JwtProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private static final String REFRESH_PREFIX = "refresh:";

	private final RedisTemplate<String, String> redisTemplate;
	private final JwtProperties jwtProperties;

	@Override
	public void storeRefreshToken(String identifier, String refreshToken) {
		redisTemplate.opsForValue().set(REFRESH_PREFIX + identifier, refreshToken, jwtProperties.refreshTokenExpiration());
	}

	@Override
	public String getStoredRefreshToken(String email) {
		return redisTemplate.opsForValue().get(REFRESH_PREFIX + email);
	}

	@Override
	public void deleteRefreshToken(String email) {
		redisTemplate.delete(REFRESH_PREFIX + email);
	}

}
