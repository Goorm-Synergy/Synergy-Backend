package com.synergy.backend.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleType {
	ATTENDEE,
	ADMIN,
	RECRUITER;
}
