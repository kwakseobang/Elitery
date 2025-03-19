package com.kwakmunsu.elitery.auth.oauth2.handler;


import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.ACCESS;
import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.elitery.util.CookieUtil.createCookie;

import com.kwakmunsu.elitery.auth.jwt.dto.MemberTokens;
import com.kwakmunsu.elitery.auth.jwt.token.JwtProvider;
import com.kwakmunsu.elitery.auth.oauth2.dto.CustomOAuth2Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        CustomOAuth2Member oAuth2Member = (CustomOAuth2Member) authentication.getPrincipal();
        MemberTokens memberTokens = jwtProvider.createTokens(
            oAuth2Member.getMemberId(),
            oAuth2Member.getRole()
        );

        response.addCookie(createCookie(REFRESH.getValue(), memberTokens.refreshToken()));
        String redirectUrl = createRedirectUrl(memberTokens.accessToken());
        log.info("OAuth2 login 성공 :" + oAuth2Member.getName());
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private String createRedirectUrl(String accessToken) {
        // TODO: 배포 시 url 수정 예정
        return UriComponentsBuilder.fromUriString("http://localhost:3000/")
            .queryParam(ACCESS.getValue(), accessToken)
            .build()
            .toUriString();
    }

}