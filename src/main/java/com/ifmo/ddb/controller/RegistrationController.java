package com.ifmo.ddb.controller;

import com.ifmo.ddb.entity.User;
import com.ifmo.ddb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping(value = "/registration", produces = "application/json")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            boolean registered = userService.register(user);
            return registered ? ResponseEntity.status(HttpStatus.CREATED).build() :
                    ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

    @GetMapping(value = "/me", produces = "application/json")
    public User getProfile() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUser(name);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

}
