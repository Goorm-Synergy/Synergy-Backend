package com.synergy.backend.domain.conference.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INVALID_TIME_PERIOD(400, "시작 날짜는 더 빨라야 합니다."),
    ;

    private final int code;
    private final String message;
}
