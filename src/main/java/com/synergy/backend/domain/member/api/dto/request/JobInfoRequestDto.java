package com.synergy.backend.domain.member.api.dto.request;

public record JobInfoRequestDto(
	Integer jobCode,
	Integer occupationCode,
	Boolean hiringInterested
) {
}
