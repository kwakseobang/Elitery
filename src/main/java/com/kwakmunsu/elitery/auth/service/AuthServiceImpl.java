package com.kwakmunsu.elitery.auth.service;

import com.kwakmunsu.elitery.auth.jwt.dto.MemberTokens;
import com.kwakmunsu.elitery.auth.jwt.entity.RefreshToken;
import com.kwakmunsu.elitery.auth.jwt.repository.RefreshTokenRepository;
import com.kwakmunsu.elitery.auth.jwt.token.JwtProvider;
import com.kwakmunsu.elitery.global.exception.UnAuthenticationException;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import com.kwakmunsu.elitery.member.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public MemberTokens reissue(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        refreshTokenRepository.deleteById(storedToken.getMemberId());

        if (!storedToken.isEqualToRefreshToken(refreshToken)) {
            throw new UnAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
        return generateNewTokens(storedToken.getMemberId(), storedToken.getRole());
    }

    private MemberTokens generateNewTokens(Long memberId, Role role) {
        MemberTokens memberTokens = jwtProvider.createTokens(memberId, role);
        return new MemberTokens(memberTokens.accessToken(), memberTokens.refreshToken());
    }

}