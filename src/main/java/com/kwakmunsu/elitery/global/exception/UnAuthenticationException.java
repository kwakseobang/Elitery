package com.kwakmunsu.elitery.global.exception;

import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class UnAuthenticationException extends EliteryException {

    public UnAuthenticationException(
        ErrorCode errorCode) {
        super(errorCode);
    }

    public UnAuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
