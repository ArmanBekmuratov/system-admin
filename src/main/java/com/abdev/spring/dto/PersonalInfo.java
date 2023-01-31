package com.abdev.spring.dto;

import java.time.LocalDate;

public record PersonalInfo(String firstname,
                           String lastname,
                           LocalDate birthDate) {
}
