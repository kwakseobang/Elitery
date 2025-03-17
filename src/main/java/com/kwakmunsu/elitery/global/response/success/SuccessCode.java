package com.kwakmunsu.elitery.global.response.success;

import com.kwakmunsu.elitery.global.response.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode implements StatusCode {

    // <=============== MEMBER ===============>
    CREATED_MEMBER(HttpStatus.CREATED.value(), SuccessMessage.CREATED_MEMBER.getMessage()),
    READ_MEMBER(HttpStatus.OK.value(), SuccessMessage.READ_MEMBER.getMessage()),
    UPDATE_MEMBER(HttpStatus.NO_CONTENT.value(), SuccessMessage.UPDATE_MEMBER.getMessage()),
    DELETE_MEMBER(HttpStatus.NO_CONTENT.value(), SuccessMessage.DELETE_MEMBER.getMessage()),

    // <=============== DIARY ===============>
    CREATED_DIARY(HttpStatus.CREATED.value(), SuccessMessage.CREATED_DIARY.getMessage()),
    READ_DIARY(HttpStatus.OK.value(), SuccessMessage.READ_DIARY.getMessage()),
    READ_DIARY_LIST(HttpStatus.OK.value(), SuccessMessage.READ_DIARY_LIST.getMessage()),
    UPDATE_DIARY(HttpStatus.NO_CONTENT.value(), SuccessMessage.UPDATE_DIARY.getMessage()),
    DELETE_DIARY(HttpStatus.NO_CONTENT.value(), SuccessMessage.DELETE_DIARY.getMessage()),

    // <=============== SERIES ===============>
    CREATED_CATEGORY(HttpStatus.CREATED.value(), SuccessMessage.CREATED_CATEGORY.getMessage()),
    READ_CATEGORY(HttpStatus.OK.value(), SuccessMessage.READ_CATEGORY.getMessage()),
    READ_CATEGORY_LIST(HttpStatus.OK.value(), SuccessMessage.READ_CATEGORY_LIST.getMessage()),
    UPDATE_CATEGORY(HttpStatus.NO_CONTENT.value(), SuccessMessage.UPDATE_CATEGORY.getMessage()),
    DELETE_CATEGORY(HttpStatus.NO_CONTENT.value(), SuccessMessage.DELETE_CATEGORY.getMessage()),

    // <=============== AUTH ===============>
    READ_IS_LOGIN(HttpStatus.OK.value(), SuccessMessage.READ_IS_LOGIN.getMessage()),
    LOGIN_SUCCESS(HttpStatus.OK.value(), SuccessMessage.LOGIN_SUCCESS.getMessage()),
    USERNAME_SUCCESS(HttpStatus.OK.value(), SuccessMessage.USERNAME_SUCCESS.getMessage()),
    NICKNAME_SUCCESS(HttpStatus.OK.value(), SuccessMessage.NICKNAME_SUCCESS.getMessage()),
    LOGOUT_SUCCESS(HttpStatus.OK.value(), SuccessMessage.LOGOUT_SUCCESS.getMessage()),
    UPDATE_PASSWORD(HttpStatus.NO_CONTENT.value(), SuccessMessage.UPDATE_PASSWORD.getMessage()),

    // <=============== JWT ===============>
    REISSUE_SUCCESS(HttpStatus.OK.value(), SuccessMessage.REISSUE_SUCCESS.getMessage()),
    ACCESS_TOKEN_SUCCESS(HttpStatus.OK.value(), SuccessMessage.ACCESS_TOKEN_SUCCESS.getMessage()),
    TOKEN_IS_VALID(HttpStatus.OK.value(), SuccessMessage.TOKEN_IS_VALID.getMessage()),
    ;

    private final int httpStatus;
    private final String message;

}