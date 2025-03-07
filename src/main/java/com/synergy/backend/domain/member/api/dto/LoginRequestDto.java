package com.synergy.backend.domain.member.api.dto;

public record LoginRequestDto(
	String email,
	String password
) {
}
