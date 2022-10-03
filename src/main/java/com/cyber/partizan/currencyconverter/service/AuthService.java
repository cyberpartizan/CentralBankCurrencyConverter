package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.dto.LoginCredits;

import java.util.Map;

public interface AuthService {

    Map<String, Object> registerHandler(LoginCredits credits);

    Map<String, Object> loginHandler(LoginCredits credits);
}
