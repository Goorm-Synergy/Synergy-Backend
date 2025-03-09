package com.synergy.backend.domain.member.exception;

import static com.synergy.backend.domain.member.exception.ErrorType.*;

import com.synergy.backend.global.exception.BaseErrorException;

public class NotFoundMember extends BaseErrorException {

	public NotFoundMember() {
		super(_MEMBER_NOT_FOUND.getCode(), _MEMBER_NOT_FOUND.getMessage());
	}
}
