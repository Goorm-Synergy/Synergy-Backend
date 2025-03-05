package com.synergy.backend.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

	_NOT_FOUND_MEMBER(400, "해당 사용자를 찾을 수 없습니다.");

	private final int code;
	private final String message;
}
