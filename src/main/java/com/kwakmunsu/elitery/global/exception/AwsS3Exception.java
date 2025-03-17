package com.kwakmunsu.elitery.global.exception;


import com.kwakmunsu.elitery.global.response.error.ErrorCode;

public class AwsS3Exception extends EliteryException {

    public AwsS3Exception(ErrorCode errorCode) {
        super(errorCode);
    }

    public AwsS3Exception(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
