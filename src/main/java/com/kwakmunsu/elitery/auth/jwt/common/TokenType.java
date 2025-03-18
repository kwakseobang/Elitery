package com.kwakmunsu.elitery.auth.jwt.common;

import lombok.Getter;

@Getter
public enum TokenType {

    AUTHORIZATION_HEADER("Authorization"),
    BEARER_PREFIX("Bearer "),
    ACCESS("access"),
    REFRESH("refresh"),
    ;

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

}