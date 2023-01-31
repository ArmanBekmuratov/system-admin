package com.abdev.spring.integregration.service;

import com.abdev.spring.dto.CompanyReadDto;
import com.abdev.spring.integregration.annotation.IT;
import com.abdev.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@SpringBootTest
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    @Autowired
    public CompanyServiceIT(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
