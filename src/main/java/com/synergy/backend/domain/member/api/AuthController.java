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

import com.synergy.backend.domain.member.api.dto.SignupAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAdminResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterResponseDto;
import com.synergy.backend.domain.member.entity.Member;
import com.synergy.backend.domain.member.service.AuthService;
import com.synergy.backend.global.common.ApiResponse;
import com.synergy.backend.global.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;
	private final AuthService authService;

	@PostMapping("/signup/attendee")
	public ApiResponse<?> registerAttendee(@RequestBody SignupAttendeeRequestDto request) {
		SignupAttendeeResponseDto response = authService.registerAttendee(request);
		return ApiResponse.ok(response, 200);
	}

	@PostMapping("/signup/admin")
	public ApiResponse<?> registerAdmin(@RequestBody SignupAdminRequestDto request) {
		SignupAdminResponseDto response = authService.registerAdmin(request);
		return ApiResponse.ok(response, 200);
	}

	@PostMapping("/signup/recruiter")
	public ApiResponse<?> registerRecruiter(@RequestBody SignupRecruiterRequestDto request) {
		SignupRecruiterResponseDto response = authService.registerRecruiter(request);
		return ApiResponse.ok(response, 200);
	}

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
