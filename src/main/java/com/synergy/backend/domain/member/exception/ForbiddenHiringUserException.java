package com.synergy.backend.domain.member.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.member.exception.ErrorType.*;

public class ForbiddenHiringUserException extends BaseErrorException {
    public ForbiddenHiringUserException() {
        super(_HIRING_NOT_INTERESTED.getCode(), _HIRING_NOT_INTERESTED.getMessage());
    }
}