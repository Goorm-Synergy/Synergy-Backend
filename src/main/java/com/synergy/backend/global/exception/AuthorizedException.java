package com.synergy.backend.global.exception;

import static com.synergy.backend.global.exception.ErrorType._UNAUTHORIZED;

public class AuthorizedException extends BaseErrorException {
    public AuthorizedException() {
        super(_UNAUTHORIZED.getCode(), _UNAUTHORIZED.getMessage());
    }
}
