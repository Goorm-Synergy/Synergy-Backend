package com.synergy.backend.domain.session.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.session.exception.ErrorType._NOT_FOUND_SESSION;

public class NotFoundSession extends BaseErrorException {


    public NotFoundSession() {
        super(_NOT_FOUND_SESSION.getCode(), _NOT_FOUND_SESSION.getMessage());
    }
}
