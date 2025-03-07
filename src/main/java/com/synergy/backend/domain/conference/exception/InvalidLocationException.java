package com.synergy.backend.domain.conference.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_CONFERENCE_LOCATION;

public class InvalidLocationException extends BaseErrorException {
    public InvalidLocationException() {
        super(_INVALID_CONFERENCE_LOCATION.getCode(), _INVALID_CONFERENCE_LOCATION.getMessage());
    }
}
