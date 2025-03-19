package com.kwakmunsu.elitery.util;

import com.kwakmunsu.elitery.global.exception.UnAuthenticationException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class JwtUtil {

    private JwtUtil() {
    }

    public static Long getCurrentMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new UnAuthenticationException(
                ErrorCode.UNAUTHORIZED_ERROR,
                "Security Context 에 인증 정보가 없습니다.");
        }
        return Long.parseLong(authentication.getName());
    }

}