package com.synergy.backend.domain.member.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.backend.domain.member.api.dto.LoginRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAdminRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupAttendeeRequestDto;
import com.synergy.backend.domain.member.api.dto.SignupRecruiterRequestDto;
import com.synergy.backend.domain.member.service.MemberService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup/attendee")
	public ApiResponse<?> registerAttendee(@RequestBody SignupAttendeeRequestDto request) {
		memberService.registerAttendee(request);
		return ApiResponse.ok("Attendee registered successfully!", 200);
	}

	@PostMapping("/signup/admin")
	public ApiResponse<?> registerAdmin(@RequestBody SignupAdminRequestDto request) {
		memberService.registerAdmin(request);
		return ApiResponse.ok("Admin registered successfully!", 200);
	}

	@PostMapping("/signup/recruiter")
	public ApiResponse<?> registerRecruiter(@RequestBody SignupRecruiterRequestDto request) {
		memberService.registerRecruiter(request);
		return ApiResponse.ok("Recruiter registered successfully!", 200);
	}

	// 로그인 엔드포인트
	@PostMapping("/signin")
	public ApiResponse<?> login(@RequestBody LoginRequestDto loginRequest) {
		return ApiResponse.ok(null, 200);
	}
}
