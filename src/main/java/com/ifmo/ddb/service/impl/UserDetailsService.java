package com.ifmo.ddb.service.impl;

import com.ifmo.ddb.entity.Role;
import com.ifmo.ddb.entity.User;
import com.ifmo.ddb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .build();
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(Role.ANALYTICS.name()));
            authorities.add(new SimpleGrantedAuthority(Role.DATA_FEEDER.name()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        }
        return authorities;
    }
}
