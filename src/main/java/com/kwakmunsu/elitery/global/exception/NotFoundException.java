package com.kwakmunsu.elitery.global.exception;

import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class NotFoundException extends EliteryException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}