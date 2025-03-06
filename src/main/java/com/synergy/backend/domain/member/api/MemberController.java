package com.synergy.backend.domain.member.api;

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
import com.synergy.backend.domain.member.service.MemberService;
import com.synergy.backend.global.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup/attendee")
	public ApiResponse<SignupAttendeeResponseDto> registerAttendee(@RequestBody SignupAttendeeRequestDto request) {
		SignupAttendeeResponseDto response = memberService.registerAttendee(request);
		return ApiResponse.ok(response, 200);
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
}
