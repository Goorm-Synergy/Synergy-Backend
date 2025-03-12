package com.synergy.backend.domain.member.exception;

import com.synergy.backend.global.exception.BaseErrorException;

public class InvalidPasswordException extends BaseErrorException {
	public InvalidPasswordException() {
		super(ErrorType._INVALID_PASSWORD.getCode(), ErrorType._INVALID_PASSWORD.getMessage());
	}
}
