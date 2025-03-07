package com.synergy.backend.domain.conference.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.conference.exception.ErrorType._INVALID_TIME_PERIOD;


public class InvalidTimePeriodException extends BaseErrorException {
    public InvalidTimePeriodException() {
        super(_INVALID_TIME_PERIOD.getCode(), _INVALID_TIME_PERIOD.getMessage());
    }
}
