package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationLevelType {
	HIGH_SCHOOL(1, "고등학교 졸업"),
	ASSOCIATE(2, "2~3년제 졸업"),
	BACHELOR(3, "4년제 졸업"),
	GRADUATE(4, "대학원 석/박사");

	private final Integer code;
	private final String description;

	public static EducationLevelType fromCode(int code) {
		return Arrays.stream(EducationLevelType.values())
			.filter(level -> level.code == code)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid education level code: " + code));
	}
}
