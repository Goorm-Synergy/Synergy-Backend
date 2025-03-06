package com.synergy.backend.domain.member.api.dto;

public record SignupRecruiterRequestDto(
	String name,
	String email,
	String password,
	String company,
	String responsibility
) {

}
