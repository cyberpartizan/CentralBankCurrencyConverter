package com.cyber.partizan.currencyconverter.security;

import com.cyber.partizan.currencyconverter.entity.AppUser;
import com.cyber.partizan.currencyconverter.entity.Role;
import com.cyber.partizan.currencyconverter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not findUser with username = " + username));
        return new org.springframework.security.core.userdetails.User(
                username,
                appUser.getHash(),
                Collections.singletonList(new SimpleGrantedAuthority(Role.USER.name())));
    }
}