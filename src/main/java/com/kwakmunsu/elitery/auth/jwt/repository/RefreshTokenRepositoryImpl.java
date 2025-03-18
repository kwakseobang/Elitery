package com.kwakmunsu.elitery.auth.jwt.repository;


import com.kwakmunsu.elitery.auth.jwt.entity.RefreshToken;
import com.kwakmunsu.elitery.global.exception.NotFoundException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }

    @Override
    public void deleteById(long memberId) {
        refreshTokenJpaRepository.deleteById(memberId);
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenJpaRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new NotFoundException(ErrorCode.BAD_REQUEST_TOKEN));
    }

}