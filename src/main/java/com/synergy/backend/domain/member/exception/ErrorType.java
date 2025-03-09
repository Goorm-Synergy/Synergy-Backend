package com.synergy.backend.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

	_MEMBER_NOT_FOUND(400, "해당 사용자를 찾을 수 없습니다."),
	_ACCESS_DENIED(403, "접근 권한이 없습니다.");

	private final int code;
	private final String message;
}
