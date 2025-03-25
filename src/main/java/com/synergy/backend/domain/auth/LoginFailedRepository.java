package com.synergy.backend.domain.auth;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoginFailedRepository {

	private static final long LOCK_TTL_MINUTES = 30;
	private static final String PREFIX_FOR_KEY = "ULF: ";

	private final RedisTemplate<String, Object> redisTemplate;

	public void setValue(String username, Integer failedCount) {
		String key = getKey(username);
		redisTemplate.opsForValue().set(key, failedCount, Duration.ofMillis(LOCK_TTL_MINUTES));
		log.info("[LoginFailedRepository]count login failed for user : {}", username);
	}

	public Integer getValues(String username) {

		return (Integer)redisTemplate.opsForValue().get(getKey(username));
	}

	private String getKey(String username) {
		return PREFIX_FOR_KEY + username;
	}

	public Long increment(String username) {
		return redisTemplate.opsForValue().increment(getKey(username));
	}

	public void delete(String username) {
		redisTemplate.delete(getKey(username));
	}
}
