package com.abdev.spring.service;

import com.abdev.spring.database.repository.CompanyRepository;
import com.abdev.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;


}
