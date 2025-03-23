package com.synergy.backend.global.token.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

	_INVALID_REFRESH_TOKEN(400, "이전에 사용한 비밀번호와 동일한 비밀번호는 사용할 수 없습니다."),
	;

	private final int code;
	private final String message;
}
