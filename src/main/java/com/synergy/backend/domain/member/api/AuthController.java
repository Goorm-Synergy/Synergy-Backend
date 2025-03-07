package com.synergy.backend.domain.member.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.LoginRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAdminResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterResponseDto;
import com.synergy.backend.domain.member.api.dto.TokenResponseDto;
import com.synergy.backend.domain.member.entity.RoleType;
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

	@PostMapping("/attendee/signup")
	public ApiResponse<?> registerAttendee(@RequestBody SignupAttendeeRequestDto request) {
		SignupAttendeeResponseDto response = authService.registerAttendee(request);
		return ApiResponse.ok(response, 201);
	}

	@PostMapping("/admin/signup")
	public ApiResponse<?> registerAdmin(@RequestBody SignupAdminRequestDto request) {
		SignupAdminResponseDto response = authService.registerAdmin(request);
		return ApiResponse.ok(response, 201);
	}

	@PostMapping("/recruiter/signup")
	public ApiResponse<?> registerRecruiter(@RequestBody SignupRecruiterRequestDto request) {
		SignupRecruiterResponseDto response = authService.registerRecruiter(request);
		return ApiResponse.ok(response, 201);
	}

	@PostMapping("/attendee/login")
	public ApiResponse<TokenResponseDto> loginAttendee(@RequestBody LoginRequestDto request) {
		return ApiResponse.ok(authService.login(request, RoleType.ATTENDEE), 200);
	}

	@PostMapping("/admin/login")
	public ApiResponse<TokenResponseDto> loginAdmin(@RequestBody LoginRequestDto request) {
		return ApiResponse.ok(authService.login(request, RoleType.ADMIN), 200);
	}

	@PostMapping("/recruiter/login")
	public ApiResponse<TokenResponseDto> loginRecruiter(@RequestBody LoginRequestDto request) {
		return ApiResponse.ok(authService.login(request, RoleType.RECRUITER), 200);
	}
}
