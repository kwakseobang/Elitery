package com.kwakmunsu.elitery.global.exception;


import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class InternalServerException extends EliteryException {

    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InternalServerException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}