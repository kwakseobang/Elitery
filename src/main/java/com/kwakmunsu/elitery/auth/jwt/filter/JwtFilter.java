package com.kwakmunsu.elitery.auth.jwt.filter;

import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.AUTHORIZATION_HEADER;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.BEARER_PREFIX;

import com.kwakmunsu.elitery.auth.jwt.response.JwtErrorResponder;
import com.kwakmunsu.elitery.auth.jwt.token.JwtProvider;
import com.kwakmunsu.elitery.global.response.error.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtErrorResponder jwtErrorResponder;
    private static final List<String> EXCLUDE_PATHS = List.of(
        "/",
        "/oauth2/**", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**", "/auth/**"
    );
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return EXCLUDE_PATHS.stream()
            .anyMatch(exclude -> pathMatcher.match(exclude, path));
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if (!StringUtils.hasText(token)) {
            jwtErrorResponder.sendErrorResponse(response, ErrorCode.WRONG_AUTH_HEADER);
            return;
        }
        if (jwtProvider.validateToken(token)) {
            jwtErrorResponder.sendErrorResponse(response, ErrorCode.INVALID_TOKEN);
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER.getValue());
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX.getValue())) {
            return bearerToken.substring(BEARER_PREFIX.getValue().length());
        }

        return null;
    }

}