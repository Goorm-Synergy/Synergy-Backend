package com.synergy.backend.domain.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeGroup {
	AGE_20_24("20~24세 이하"),
	AGE_25_29("25~29세 이하"),
	AGE_30_34("30~34세 이하"),
	AGE_35_PLUS("35세 이상");

	private final String description;

}
