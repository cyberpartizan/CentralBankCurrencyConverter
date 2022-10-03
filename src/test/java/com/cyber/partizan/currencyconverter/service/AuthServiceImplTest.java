package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.dto.LoginCredits;
import com.cyber.partizan.currencyconverter.entity.AppUser;
import com.cyber.partizan.currencyconverter.utils.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    UserService userService;
    @Mock
    JWTUtil jwtUtil;
    @Mock
    AuthenticationManager authManager;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    AuthServiceImpl authService;


    private LoginCredits credits;
    private final String jwtToken = "THIS_IS_JWT_TOKEN";
    @BeforeEach
    void beforeEach(){
        credits = LoginCredits.builder().password("password").username("username").build();
    }

    @Test
    void registerHandler() {
        //Preparation
        String hash = "thisishashvalue_ddashjkdbjkahsbajshk";
        AppUser user = new AppUser(null, credits.getUsername(), hash);
        when(passwordEncoder.encode(credits.getPassword())).thenReturn(hash);
        when(userService.save(any())).thenReturn(user);
        when(jwtUtil.generateToken(user.getUsername())).thenReturn(jwtToken);

        //Action
        authService.registerHandler(credits);

        //Assertion
        verify(passwordEncoder).encode(credits.getPassword());
        verify(userService).save(any());
        verify(jwtUtil).generateToken(user.getUsername());
    }

    @Test
    void loginHandler() {
        //Preparation
        when(authManager.authenticate(any())).thenReturn(null);
        when(jwtUtil.generateToken(credits.getUsername())).thenReturn(jwtToken);

        //Action
        authService.loginHandler(credits);

        //Assertion
        verify(jwtUtil).generateToken(credits.getUsername());
    }
}