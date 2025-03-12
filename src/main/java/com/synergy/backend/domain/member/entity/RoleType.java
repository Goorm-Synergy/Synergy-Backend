package com.synergy.backend.domain.member.entity;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RoleType implements GrantedAuthority {
	ATTENDEE,
	ADMIN,
	RECRUITER;

	public String getRole() {
		return this.name();
	}

	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}
}
