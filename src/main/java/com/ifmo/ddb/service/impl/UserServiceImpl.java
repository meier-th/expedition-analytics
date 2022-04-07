package com.ifmo.ddb.service.impl;

import com.ifmo.ddb.entity.User;
import com.ifmo.ddb.entity.repo.UserRepository;
import com.ifmo.ddb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public boolean register(User user) {
        if (!repository.existsById(user.getUsername())) {
            user.setPassword(encoder.encode(user.getPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User findUser(String username) {
        return repository.findById(username).orElse(null);
    }
}
