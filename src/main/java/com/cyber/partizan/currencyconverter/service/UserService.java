package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.AppUser;

public interface UserService {
    AppUser save(AppUser user);

    boolean userExist(String username);
}
