package com.kwakmunsu.elitery.global.response.success;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    // <=============== MEMBER ===============>
    CREATED_MEMBER("SUCCESS - 회원 가입 성공"),
    READ_MEMBER("SUCCESS - 회원 정보 조회 성공"),
    UPDATE_MEMBER("SUCCESS - 회원 정보 수정 성공"),
    DELETE_MEMBER("SUCCESS - 회원 정보 삭제 성공"),

    // <=============== DIARY ===============>
    CREATED_DIARY("SUCCESS - 운동 일지 등록 성공"),
    READ_DIARY("SUCCESS - 운동 일지 조회 성공"),
    READ_DIARY_LIST("SUCCESS - 운동 일지 목록 조회 성공"),
    UPDATE_DIARY("SUCCESS - 운동 일지 수정 성공"),
    DELETE_DIARY("SUCCESS - 운동 일지 삭제"),

    // <=============== CATEGORY ===============>
    CREATED_CATEGORY("SUCCESS - 카테고리 생성 성공"),
    READ_CATEGORY_LIST("SUCCESS - 카테고리 목록 조회 성공"),
    READ_CATEGORY("SUCCESS - 카테고리 조회 성공"),
    UPDATE_CATEGORY("SUCCESS - 카테고리 수정 성공"),
    DELETE_CATEGORY("SUCCESS - 카테고리 삭제 성공"),

    // <=============== AUTH ===============>
    LOGIN_SUCCESS("SUCCESS - 로그인 성공"),
    LOGOUT_SUCCESS("SUCCESS - 로그아웃 성공 및 user Refresh Token 삭제"),
    UPDATE_PASSWORD("SUCCESS - 비밀번호 수정 성공"),
    USERNAME_SUCCESS("SUCCESS - 사용 가능한 아이디입니다."),
    NICKNAME_SUCCESS("SUCCESS - 사용 가능한 닉네임입니다."),
    READ_IS_LOGIN("SUCCESS - 현재 로그인 여부 조회 성공"),

    // <=============== JWT ===============>
    TOKEN_IS_VALID("VALID - 유효한 토큰"),
    REISSUE_SUCCESS("SUCCESS - JWT Access 토큰 재발급 성공"),
    ACCESS_TOKEN_SUCCESS("SUCCESS - JWT Access 토큰 GET "),
    ;

    private final String message;

}