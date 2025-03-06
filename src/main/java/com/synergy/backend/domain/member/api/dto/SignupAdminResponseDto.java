package com.synergy.backend.domain.member.api.dto;

public record SignupAdminResponseDto(
	String name,
	String email,
	String password, String assignedAdminId

) {

}
