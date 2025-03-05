package com.synergy.backend.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.LoginRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRequestDto;
import com.synergy.backend.domain.member.service.MemberService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	// 회원가입 엔드포인트
	@PostMapping("/signup")
	public ApiResponse<?> signup(@RequestBody SignupRequestDto request) {
		return ApiResponse.ok(null, 200);
	}

	// 로그인 엔드포인트
	@PostMapping("/signin")
	public ApiResponse<?> login(@RequestBody LoginRequestDto loginRequest) {
		return ApiResponse.ok(null, 200);
	}
}
