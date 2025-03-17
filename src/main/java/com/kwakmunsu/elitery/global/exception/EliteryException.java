package com.kwakmunsu.elitery.global.exception;

import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import lombok.Getter;

@Getter
public abstract class EliteryException extends RuntimeException {

    private final ErrorCode errorCode;
    private String message;

    protected EliteryException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    protected EliteryException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}