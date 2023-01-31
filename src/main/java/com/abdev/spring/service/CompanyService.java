package com.abdev.spring.service;

import com.abdev.spring.database.entity.AccessType;
import com.abdev.spring.database.entity.EntityEvent;
import com.abdev.spring.database.repository.CompanyRepository;
import com.abdev.spring.dto.CompanyReadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private final UserService userService;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public CompanyService(UserService userService, CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.companyRepository = companyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}
