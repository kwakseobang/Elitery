package com.kwakmunsu.elitery.global.response.error;

import com.kwakmunsu.elitery.global.response.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements StatusCode {

    // <=============== MEMBER ===============>
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), ErrorMessage.NOT_FOUND_MEMBER.getMessage()),
    BAD_REQUEST_MEMBER(
        HttpStatus.BAD_REQUEST.value(),
        ErrorMessage.BAD_REQUEST_MEMBER.getMessage()
    ),
    BAD_REQUEST_PASSWORD(
        HttpStatus.BAD_REQUEST.value(),
        ErrorMessage.BAD_REQUEST_PASSWORD.getMessage()
    ),
    DUPLICATE_USERNAME(
        HttpStatus.BAD_REQUEST.value(),
        ErrorMessage.DUPLICATE_USERNAME.getMessage()
    ),
    DUPLICATE_NICKNAME(
        HttpStatus.BAD_REQUEST.value(),
        ErrorMessage.DUPLICATE_NICKNAME.getMessage()
    ),
    // <=============== POST ===============>
    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(), ErrorMessage.NOT_FOUND_DIARY.getMessage()),
    BAD_REQUEST_POST(HttpStatus.BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_DIARY.getMessage()),

    // <=============== SERIES ===============>
    NOT_FOUND_SERIES(HttpStatus.NOT_FOUND.value(), ErrorMessage.NOT_FOUND_CATEGORY.getMessage()),
    BAD_REQUEST_SERIES(HttpStatus.BAD_REQUEST.value(),
        ErrorMessage.BAD_REQUEST_CATEGORY.getMessage()),
    DUPLICATE_SERIES(HttpStatus.BAD_REQUEST.value(), ErrorMessage.DUPLICATE_CATEGORY.getMessage()),

    // <=============== JWT ===============>
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED.value(), ErrorMessage.TOKEN_EXPIRED.getMessage()),
    TOKEN_ERROR(HttpStatus.UNAUTHORIZED.value(), ErrorMessage.TOKEN_ERROR.getMessage()),
    BAD_REQUEST_TOKEN(HttpStatus.BAD_REQUEST.value(), ErrorMessage.BAD_REQUEST_TOKEN.getMessage()),
    TOKEN_IS_BLACKLIST(
        HttpStatus.UNAUTHORIZED.value(),
        ErrorMessage.TOKEN_IS_BLACKLIST.getMessage()
    ),
    TOKEN_HASH_NOT_SUPPORTED(
        HttpStatus.UNAUTHORIZED.value(),
        ErrorMessage.TOKEN_HASH_NOT_SUPPORTED.getMessage()
    ),
    WRONG_AUTH_HEADER(
        HttpStatus.UNAUTHORIZED.value(),
        ErrorMessage.WRONG_AUTH_HEADER.getMessage()
    ),
    TOKEN_VALIDATION_TRY_FAILED(
        HttpStatus.UNAUTHORIZED.value(),
        ErrorMessage.TOKEN_VALIDATION_TRY_FAILED.getMessage()
    ),

    // <=============== AWS ===============>
    AWS_S3_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.AWS_S3_ERROR.getMessage()),
    AWS_S3_DELETE_ERROR(
        HttpStatus.NOT_FOUND.value(),
        ErrorMessage.AWS_S3_DELETE_ERROR.getMessage()
    ),
    AWS_S3_UPLOAD_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorMessage.AWS_S3_UPLOAD_ERROR.getMessage()
    ),
    AWS_S3_TYPE_ERROR(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
        ErrorMessage.AWS_S3_TYPE_ERROR.getMessage()
    ),

    // <=============== ETC ===============>
    PREVENT_GET_ERROR(
        HttpStatus.NO_CONTENT.value(),
        ErrorMessage.PREVENT_GET_ERROR.getMessage()
    ),
    INTERNAL_SERVER_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()
    ),
    UNAUTHORIZED_ERROR(
        HttpStatus.UNAUTHORIZED.value(),
        ErrorMessage.UNAUTHORIZED.getMessage()
    ),
    FORBIDDEN_ERROR(
        HttpStatus.FORBIDDEN.value(),
        ErrorMessage.FORBIDDEN.getMessage()
    ),
    ;

    private final int httpStatus;
    private final String message;

}