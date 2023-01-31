package com.abdev.spring.integregration.service;

import com.abdev.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Test
    void test() {

    }
}
