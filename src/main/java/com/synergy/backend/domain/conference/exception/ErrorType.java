package com.synergy.backend.domain.conference.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    _NOT_FOUND_CONFERENCE(400, "해당 컨퍼런스가 존재하지 않습니다.");

    private final int code;
    private final String message;
}
