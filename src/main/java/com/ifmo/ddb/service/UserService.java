package com.ifmo.ddb.service;

import com.ifmo.ddb.entity.User;

public interface UserService {

    boolean register(User user);
    User findUser(String username);

}
