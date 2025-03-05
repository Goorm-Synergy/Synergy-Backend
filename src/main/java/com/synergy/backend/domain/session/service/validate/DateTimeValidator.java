package com.synergy.backend.domain.session.service.validate;

import com.synergy.backend.domain.session.exception.NotValidSessionTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {

    public static LocalDateTime isValidLocalDateTime(String dateTimeStr) {
        try{
            return LocalDateTime.parse(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new NotValidSessionTime();
        }
    }
}
