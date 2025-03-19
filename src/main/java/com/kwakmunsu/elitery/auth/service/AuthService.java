package com.kwakmunsu.elitery.auth.service;

import com.kwakmunsu.elitery.auth.jwt.dto.MemberTokens;

public interface AuthService {

    MemberTokens reissue(String refreshTokenRequest);
}