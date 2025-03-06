package com.synergy.backend.domain.member.api;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;

	@PostMapping("/signin")
	public ApiResponse<?> login(@RequestBody Map<String, String> credentials) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(credentials.get("email"), credentials.get("password"))
		);
		UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.get("email"));
		String token = jwtProvider.generateToken((Member)userDetails);
		return ApiResponse.ok(Map.of("token", token), 200);
	}
}
