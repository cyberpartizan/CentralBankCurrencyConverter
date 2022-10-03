package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.dto.LoginCredits;
import com.cyber.partizan.currencyconverter.entity.AppUser;
import com.cyber.partizan.currencyconverter.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> registerHandler(LoginCredits credits) {
        String hash = passwordEncoder.encode(credits.getPassword());
        var newUser = new AppUser(null, credits.getUsername(), hash);
        newUser.setHash(hash);
        newUser = userService.save(newUser);
        String token = jwtUtil.generateToken(newUser.getUsername());
        return Collections.singletonMap("jwt", token);
    }

    @Override
    public Map<String, Object> loginHandler(LoginCredits credits) {
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
