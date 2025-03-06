package com.synergy.backend.domain.member.api.dto;

public record SignupAdminRequestDto(
	String name,
	String email,
	String password,
	String assignedAdminId
) {

}
