package com.synergy.backend.domain.member.api.dto.resposne;

import com.synergy.backend.domain.member.entity.RoleType;

public record TokenResponseDto(
	String accessToken,
	// String refreshToken,
	String identifier,
	RoleType role) {
}
