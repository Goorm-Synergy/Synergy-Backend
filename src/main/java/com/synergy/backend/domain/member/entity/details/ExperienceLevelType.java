package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExperienceLevelType {
	NEWCOMER(1, "신입"),
	JUNIOR(2, "1~2년 이하"),
	MID_LEVEL(3, "3~4년 이하"),
	SENIOR(4, "5년 이상");

	private final Integer code;
	private final String description;

	public static ExperienceLevelType fromCode(int code) {
		return Arrays.stream(ExperienceLevelType.values())
			.filter(level -> level.code == code)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid experience level code: " + code));
	}
}
