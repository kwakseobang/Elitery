package com.kwakmunsu.elitery.global.exception;

import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class DuplicationException extends EliteryException {

    public DuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DuplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}