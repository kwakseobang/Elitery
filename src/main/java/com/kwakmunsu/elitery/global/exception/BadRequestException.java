package com.kwakmunsu.elitery.global.exception;

import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class BadRequestException extends EliteryException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}