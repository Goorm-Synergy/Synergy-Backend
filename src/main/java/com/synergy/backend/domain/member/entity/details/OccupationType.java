package com.synergy.backend.domain.member.entity.details;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 직무
@Getter
@AllArgsConstructor
public enum OccupationType {
	DEVELOPMENT(1, "Development"),
	DESIGN(2, "Design"),
	PLANNING_OPERATION(3, "Planning & Operation"),
	OTHER(4, "Other");

	private final int id; // DB에서 사용하는 ID 값
	private final String description;

	public static OccupationType fromId(int id) {
		return Arrays.stream(OccupationType.values())
			.filter(type -> type.id == id)
			.findFirst()
			.orElse(null);
	}
}
