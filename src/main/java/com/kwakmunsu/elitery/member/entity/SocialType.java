package com.kwakmunsu.elitery.member.entity;

import lombok.Getter;

@Getter
public enum SocialType {

    GOOGLE, NAVER, KAKAO;

    public static SocialType from(String value) {
        return SocialType.valueOf(value.toUpperCase());
    }

}