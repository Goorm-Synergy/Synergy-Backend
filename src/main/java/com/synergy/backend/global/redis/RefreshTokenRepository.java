package com.synergy.backend.global.redis;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepository {

	private final RedisTemplate<String, Object> redisTemplate;
	private static final long REFRESH_TOKEN_TTL = 7 * 24 * 60 * 60; // 7일

	public void save(String userId, String refreshToken) {
		redisTemplate.opsForValue().set("RT:" + userId, refreshToken, REFRESH_TOKEN_TTL, TimeUnit.SECONDS);
	}

	public Optional<String> findByUserId(String userId) {
		String refreshToken = (String) redisTemplate.opsForValue().get("RT:" + userId);
		return Optional.ofNullable(refreshToken);
	}

	public void delete(String userId) {
		redisTemplate.delete("RT:" + userId);
	}
}
