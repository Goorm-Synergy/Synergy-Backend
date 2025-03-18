package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import com.synergy.backend.domain.member.exception.InvalidAgeGroupCodeException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgeGroup {
	AGE_20_24(20, "20~24세 이하"),
	AGE_25_29(25, "25~29세 이하"),
	AGE_30_34(30, "30~34세 이하"),
	AGE_35_PLUS(35, "35세 이상");

	private final Integer code;
	private final String description;

	public static AgeGroup fromCode(int code) {
		return Arrays.stream(AgeGroup.values())
			.filter(group -> group.code == code)
			.findFirst()
			.orElseThrow(InvalidAgeGroupCodeException::new);
	}
}
