package com.synergy.backend.domain.member.entity;

import lombok.Getter;

@Getter
public enum ExperienceLevelType {
	NEWCOMER("신입"),
	JUNIOR("1~2년 이하"),
	MID_LEVEL("3~4년 이하"),
	SENIOR("5년 이상");

	private final String description;

	ExperienceLevelType(String description) {
		this.description = description;
	}
}
