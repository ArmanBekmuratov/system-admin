package com.abdev.spring.service;

import com.abdev.spring.database.repository.UserRepository;


public class UserService {

    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
