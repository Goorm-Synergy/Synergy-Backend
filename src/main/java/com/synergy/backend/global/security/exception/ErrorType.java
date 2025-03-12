package com.synergy.backend.global.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

	_UNKNOWN_USER_TYPE(400, "존재하지 않는 RoleType입니다."),
	;

	private final int code;
	private final String message;
}
