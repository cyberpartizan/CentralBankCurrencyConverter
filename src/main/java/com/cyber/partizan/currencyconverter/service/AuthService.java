package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.dto.LoginCredits;
import com.cyber.partizan.currencyconverter.dto.UserWrapper;

import java.util.Map;

public interface AuthService {

    Map<String, Object> registerHandler(UserWrapper userWrapper);

    Map<String, Object> loginHandler(LoginCredits credits);
}
