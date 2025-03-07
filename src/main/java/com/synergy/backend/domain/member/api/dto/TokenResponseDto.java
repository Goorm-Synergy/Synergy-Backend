package com.synergy.backend.domain.member.api.dto;

public record TokenResponseDto(
	String accessToken,
	String refreshToken,
	String role) {
}
