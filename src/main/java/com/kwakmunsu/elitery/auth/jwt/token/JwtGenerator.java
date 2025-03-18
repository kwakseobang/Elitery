package com.kwakmunsu.elitery.auth.jwt.token;

import static com.kwakmunsu.elitery.auth.jwt.common.TokenExpiration.ACCESS_TOKEN;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenExpiration.REFRESH_TOKEN;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.ACCESS;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.AUTHORIZATION_HEADER;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.REFRESH;

import com.kwakmunsu.elitery.auth.jwt.entity.RefreshToken;
import com.kwakmunsu.elitery.auth.jwt.repository.RefreshTokenRepository;
import com.kwakmunsu.elitery.member.entity.Role;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;


@Component
public class JwtGenerator {

    private static final String CATEGORY_KEY = "category";
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtGenerator(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createAccessToken(Long memberId, Role role, SecretKey secretKey) {
        Date date = new Date();
        Date validity = new Date(date.getTime() + ACCESS_TOKEN.getExpirationTime());
        System.out.println(secretKey);
        return Jwts.builder()
            .subject(String.valueOf(memberId))
            .claim(CATEGORY_KEY, ACCESS.getValue())
            .claim(AUTHORIZATION_HEADER.getValue(), role)
            .expiration(validity)
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(SecretKey secretKey) {
        Date date = new Date();
        Date validity = new Date(date.getTime() + REFRESH_TOKEN.getExpirationTime());
        return Jwts.builder()
            .claim(CATEGORY_KEY, REFRESH.getValue())
            .expiration(validity)
            .signWith(secretKey)
            .compact();
    }

    public void saveRefreshToken(Long memberId, String refreshToken, Role role) {
        RefreshToken newRefreshToken = RefreshToken.builder()
            .memberId(memberId)
            .refreshToken(refreshToken)
            .role(role)
            .build();
        refreshTokenRepository.save(newRefreshToken);
    }

    public RefreshToken getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

}