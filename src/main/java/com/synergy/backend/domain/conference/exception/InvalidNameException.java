package com.synergy.backend.domain.conference.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_CONFERENCE_NAME;


public class InvalidNameException extends BaseErrorException {
    public InvalidNameException() {
        super(_INVALID_CONFERENCE_NAME.getCode(), _INVALID_CONFERENCE_NAME.getMessage());
    }
}

