package com.kwakmunsu.elitery.auth.jwt.token;


import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.AUTHORIZATION_HEADER;

import com.kwakmunsu.elitery.auth.jwt.dto.MemberTokens;
import com.kwakmunsu.elitery.auth.jwt.entity.RefreshToken;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import com.kwakmunsu.elitery.member.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {

    private final JwtGenerator jwtGenerator;
    private final JwtCleaner jwtCleaner;
    private final SecretKey secretKey;

    public JwtProvider(
        JwtGenerator jwtGenerator,
        JwtCleaner jwtCleaner,
        @Value("${spring.jwt.secretKey}") String key
    ) {
        this.jwtGenerator = jwtGenerator;
        this.jwtCleaner = jwtCleaner;
        this.secretKey = new SecretKeySpec(
            key.getBytes(StandardCharsets.UTF_8),
            Jwts.SIG.HS256
                .key()
                .build()
                .getAlgorithm()
        );
    }

    public MemberTokens createTokens(Long memberId, Role role) {
        String accessToken = jwtGenerator.createAccessToken(memberId, role, secretKey);
        String refreshToken = jwtGenerator.createRefreshToken(secretKey);
        jwtGenerator.saveRefreshToken(memberId, refreshToken, role);
        return new MemberTokens(accessToken, refreshToken);
    }

    public MemberTokens reissueToken(String refreshToken) {
        if (!validateToken(refreshToken)) {
            throw new JwtException(ErrorCode.TOKEN_ERROR.getMessage() + refreshToken);
        }
        RefreshToken storedToken = jwtGenerator.getRefreshToken(refreshToken);
        Long memberId = storedToken.getMemberId();
        jwtCleaner.deleteRefreshToken(memberId);

        if (!refreshToken.equals(storedToken.getRefreshToken())) {
            throw new JwtException(ErrorCode.TOKEN_ERROR.getMessage() + refreshToken);
        }
        Role role = storedToken.getRole();
        String newAccessToken = jwtGenerator.createAccessToken(memberId, role, secretKey);
        String newRefreshToken = jwtGenerator.createRefreshToken(secretKey);
        jwtGenerator.saveRefreshToken(memberId, newRefreshToken, role);

        return new MemberTokens(newAccessToken, newRefreshToken);
    }

    public Authentication getAuthentication(String token) {
        String memberId = getClaimsFromToken(token).getSubject();
        // 유저 권한은 하나밖에 없기에 singletonList 로 진행함. 단 하나의 권한만 가질때 사용.
        GrantedAuthority authority = new SimpleGrantedAuthority(getAuthority(token).getValue());
        return new UsernamePasswordAuthenticationToken(
            memberId,
            null,
            Collections.singletonList(authority)
        );
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            validateExpiredToken(claims);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn(ErrorCode.TOKEN_ERROR.getMessage() + token);
        } catch (ExpiredJwtException e) {
            log.warn(ErrorCode.TOKEN_EXPIRED.getMessage() + token);
        } catch (UnsupportedJwtException e) {
            log.warn(ErrorCode.TOKEN_HASH_NOT_SUPPORTED.getMessage() + token);
        } catch (IllegalArgumentException e) {
            log.warn(ErrorCode.BAD_REQUEST_TOKEN.getMessage() + token);
        }
        return false;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private Role getAuthority(String token) {
        return getClaimsFromToken(token)
            .get(AUTHORIZATION_HEADER.getValue(), Role.class);
    }

    private void validateExpiredToken(Claims claims) {
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            throw new ExpiredJwtException(null, claims, ErrorCode.TOKEN_EXPIRED.getMessage());
        }
    }

}