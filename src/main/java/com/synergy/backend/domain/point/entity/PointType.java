package com.synergy.backend.domain.point.entity;

import lombok.Getter;

@Getter
public enum PointType {
	SIGN_UP(50),
	BOOTH_VISIT(20),
	SESSION_ATTEND(30),
	SESSION_QNA(50),
	SURVEY_PARTICIPATION(30),
	CONTENT_SHARE(20),
	RECRUITER_MEETING(50);

	private final int pointValue;

	PointType(int pointValue) {
		this.pointValue = pointValue;
	}

}
