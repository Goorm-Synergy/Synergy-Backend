package com.synergy.backend.domain.member.entity.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum EducationLevelType {
	HIGH_SCHOOL("고등학교 졸업"),
	ASSOCIATE("2~3년제 졸업"),
	BACHELOR("4년제 졸업"),
	GRADUATE("대학원 석/박사");

	private final String description;

}
