package com.cyber.partizan.currencyconverter.controller;


import com.cyber.partizan.currencyconverter.dto.LoginCredits;
import com.cyber.partizan.currencyconverter.dto.UserWrapper;
import com.cyber.partizan.currencyconverter.entity.AppUser;
import com.cyber.partizan.currencyconverter.service.UserService;
import com.cyber.partizan.currencyconverter.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody @Valid UserWrapper userWrapper) {
        String hash = passwordEncoder.encode(userWrapper.getPassword());
        var newUser = new AppUser(null, userWrapper.getUsername(), hash);
        newUser.setHash(hash);
        newUser = userService.save(newUser);
        String token = jwtUtil.generateToken(newUser.getUsername());
        return Collections.singletonMap("jwt", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody @Valid LoginCredits credits) {
        try {
            var authInputToken = new UsernamePasswordAuthenticationToken(credits.getUsername(), credits.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(credits.getUsername());
            return Collections.singletonMap("jwt", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }


}