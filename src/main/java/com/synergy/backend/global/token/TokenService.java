package com.synergy.backend.global.token;

public interface TokenService {

	void storeRefreshToken(String email, String refreshToken);

	String getStoredRefreshToken(String email);

	void deleteRefreshToken(String email);

}
