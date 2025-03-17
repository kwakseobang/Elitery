package com.kwakmunsu.elitery.member.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum SocialType {

    GOOGLE, NAVER, KAKAO;

    @JsonCreator
    public static SocialType from(String value) {
        return SocialType.valueOf(value.toUpperCase());
    }

}