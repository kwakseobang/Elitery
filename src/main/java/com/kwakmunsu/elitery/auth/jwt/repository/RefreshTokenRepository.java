package com.kwakmunsu.elitery.auth.jwt.repository;

import com.kwakmunsu.elitery.auth.jwt.entity.RefreshToken;

public interface RefreshTokenRepository {
     void save(RefreshToken refreshToken);
     void deleteById(long memberId);
     RefreshToken findByRefreshToken(String refreshToken);
}