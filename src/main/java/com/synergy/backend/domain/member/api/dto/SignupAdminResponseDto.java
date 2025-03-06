package com.synergy.backend.domain.member.api.dto;

import com.synergy.backend.domain.member.entity.Admin;

public record SignupAdminResponseDto(
	String name,
	String email
) {
	public static SignupAdminResponseDto from(Admin admin) {
		return new SignupAdminResponseDto(admin.getName(), admin.getEmail());
	}
}
