package com.cyber.partizan.currencyconverter.controller;


import com.cyber.partizan.currencyconverter.dto.LoginCredits;
import com.cyber.partizan.currencyconverter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody @Valid LoginCredits credits) {
       return authService.registerHandler(credits);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody @Valid LoginCredits credits) {
        return authService.loginHandler(credits);
    }


}