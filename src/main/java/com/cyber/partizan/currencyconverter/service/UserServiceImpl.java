package com.cyber.partizan.currencyconverter.service;

import com.cyber.partizan.currencyconverter.entity.AppUser;
import com.cyber.partizan.currencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser save(AppUser user){
        return userRepository.save(user);
    }

    @Override
    public boolean userExist(String username){
        return userRepository.existsByUsername(username);
    }

}
