package com.synergy.backend.domain.conference.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.conference.exception.ErrorType._NOT_FOUND_CONFERENCE;

public class NotFoundConference extends BaseErrorException {
    public NotFoundConference() {
        super(_NOT_FOUND_CONFERENCE.getCode(), _NOT_FOUND_CONFERENCE.getMessage());
    }
}
