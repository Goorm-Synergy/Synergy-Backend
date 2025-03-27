package com.synergy.backend.global.token;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {
	public static final String REFRESH_TOKEN_NAME = "refreshToken";

	public String extractRefreshToken(HttpServletRequest request) {
		if (request.getCookies() == null)
			return null;

		return Arrays.stream(request.getCookies())
			.filter(cookie -> REFRESH_TOKEN_NAME.equals(cookie.getName()))
			.map(Cookie::getValue)
			.findFirst()
			.orElse(null);
	}

	public void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
		ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_NAME, refreshToken)
			.httpOnly(true)
			.secure(false)	// .secure(ture): http에서 안됨 추후 변경
			.path("/")
			.maxAge(Duration.ofDays(30))
			.sameSite("None")
			.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}

	public void deleteRefreshTokenCookie(HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_NAME, "")
			.httpOnly(true)
			.secure(false)	// .secure(ture): http에서 안됨 추후 변경
			.path("/")
			.maxAge(0)
			.sameSite("None")
			.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}
}
