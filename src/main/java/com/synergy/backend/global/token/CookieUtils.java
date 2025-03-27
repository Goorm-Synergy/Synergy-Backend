package com.synergy.backend.global.token;

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
		Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, refreshToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge(30 * 24 * 60 * 60); // 30일

		// 필요 시 SameSite=Lax 혹은 Strict 설정 (Spring 6 이상이나 ResponseHeaderFilter 필요)
		response.addCookie(cookie);
	}

	public void deleteRefreshTokenCookie(HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_NAME, "")
			.path("/")
			.httpOnly(true)
			.maxAge(0) // 바로 만료
			.build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}
}
