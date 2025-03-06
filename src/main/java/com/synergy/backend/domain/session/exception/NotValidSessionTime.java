package com.synergy.backend.domain.session.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.session.exception.ErrorType._NOT_VALID_SESSION_TIME;

public class NotValidSessionTime extends BaseErrorException {
    public NotValidSessionTime() {
        super(_NOT_VALID_SESSION_TIME.getCode(), _NOT_VALID_SESSION_TIME.getMessage());
    }
}
