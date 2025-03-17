package com.kwakmunsu.elitery.auth.controller;

import com.kwakmunsu.elitery.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

}