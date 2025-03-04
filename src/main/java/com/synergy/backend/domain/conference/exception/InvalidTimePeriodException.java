package com.synergy.backend.domain.conference.exception;

import com.synergy.backend.global.exception.BaseErrorException;

import static com.synergy.backend.domain.conference.exception.ErrorType.INVALID_TIME_PERIOD;

public class InvalidTimePeriodException extends BaseErrorException {
    public InvalidTimePeriodException() {
        super(INVALID_TIME_PERIOD.getCode(), INVALID_TIME_PERIOD.getMessage());
    }
}
