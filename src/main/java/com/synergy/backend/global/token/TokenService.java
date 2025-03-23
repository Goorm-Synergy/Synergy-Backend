package com.synergy.backend.global.token;

import java.time.Duration;

public interface TokenService {

	void storeRefreshToken(String email, String refreshToken, Duration ttl);

	String getStoredRefreshToken(String email);

	void deleteRefreshToken(String email);

}
