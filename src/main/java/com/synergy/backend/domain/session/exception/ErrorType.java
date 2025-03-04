package com.synergy.backend.domain.session.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    _NOT_FOUND_SESSION(400, "해당 세션을 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
