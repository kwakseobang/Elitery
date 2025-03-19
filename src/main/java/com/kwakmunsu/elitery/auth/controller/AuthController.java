package com.kwakmunsu.elitery.auth.controller;

import static com.kwakmunsu.elitery.auth.jwt.common.TokenType.REFRESH;
import static com.kwakmunsu.elitery.util.CookieUtil.createCookie;

import com.kwakmunsu.elitery.auth.jwt.dto.MemberTokens;
import com.kwakmunsu.elitery.auth.service.AuthService;
import com.kwakmunsu.elitery.global.response.ResponseData;
import com.kwakmunsu.elitery.global.response.success.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reissue")
    @Operation(summary = "Access Token 재발급 요청 [쿠키 사용]")
    public ResponseEntity<ResponseData<String>> reissue(
        HttpServletResponse response,
        @CookieValue("refreshToken") final String refreshTokenRequest
    ) {
        MemberTokens memberTokens = authService.reissue(refreshTokenRequest);
        response.addCookie(createCookie(REFRESH.getValue(), memberTokens.refreshToken()));
        return ResponseData.success(SuccessCode.REISSUE_SUCCESS, memberTokens.accessToken());
    }

}