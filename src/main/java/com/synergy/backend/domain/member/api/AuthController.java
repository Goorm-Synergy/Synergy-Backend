package com.synergy.backend.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.LoginAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.LoginRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeResponseDto;
import com.synergy.backend.domain.member.api.dto.TokenResponseDto;
import com.synergy.backend.domain.member.entity.RoleType;
import com.synergy.backend.domain.member.service.AuthService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/attendee/signup")
	public ApiResponse<?> registerAttendee(@RequestBody SignupAttendeeRequestDto request) {
		SignupAttendeeResponseDto response = authService.registerAttendee(request);
		return ApiResponse.ok(response, 201);
	}

	@PostMapping("/attendee/login")
	public ApiResponse<TokenResponseDto> loginAttendee(@RequestBody LoginRequestDto request) {
		return ApiResponse.ok(authService.loginAttendee(request, RoleType.ATTENDEE), 200);
	}

	@PostMapping("/admin/login")
	public ApiResponse<TokenResponseDto> loginAdmin(@RequestBody LoginAdminRequestDto request) {
		return ApiResponse.ok(authService.loginAdminOrRecruiter(request), 200);
	}

}
