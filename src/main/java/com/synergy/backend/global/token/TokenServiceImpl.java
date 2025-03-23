package com.synergy.backend.global.token;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private static final String REFRESH_PREFIX = "refresh:";

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public void storeRefreshToken(String email, String refreshToken, Duration ttl) {
		redisTemplate.opsForValue().set(REFRESH_PREFIX + email, refreshToken, ttl);
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
