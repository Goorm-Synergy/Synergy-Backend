package com.synergy.backend.domain.member.api.dto;

public record SignupRecruiterResponseDto(
	String name,
	String email,
	String password,
	String company,
	String responsibility
) {

}
