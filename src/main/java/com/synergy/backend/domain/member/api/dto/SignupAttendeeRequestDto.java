package com.synergy.backend.domain.member.api.dto;

public record SignupAttendeeRequestDto(
	String name,
	String email,
	String password,
	String phone
	// 인증 코드 추가
) {

}
