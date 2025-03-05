package com.synergy.backend.domain.conference.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    _INVALID_TIME_PERIOD(400, "시작 날짜는 더 빨라야 합니다."),
    _NOT_FOUND_CONFERENCE(400, "해당 컨퍼런스를 찾을 수 없습니다.")
    ;

    private final int code;
    private final String message;
}
