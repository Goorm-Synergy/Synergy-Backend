package com.synergy.backend.domain.member.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record JobInfoRequestDto(
	@NotNull
	Integer jobPositionCode,

	@NotNull
	Integer jobGroupCode,

	@NotNull
	Boolean hiringInterested
) {
}
