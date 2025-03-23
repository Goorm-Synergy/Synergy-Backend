package com.synergy.backend.global.token;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {
	private static final String REFRESH_TOKEN_NAME = "refresh-token";

	public String extractRefreshToken(HttpServletRequest request) {
		return "";
	}

	public void addRefreshTokenToCookie(HttpServletResponse response, String newRefreshToken) {
	}
}
