package com.abdev.spring.integregration.database.repository;

import com.abdev.spring.database.entity.Role;
import com.abdev.spring.database.entity.User;
import com.abdev.spring.database.repository.UserRepository;
import com.abdev.spring.dto.PersonalInfo;
import com.abdev.spring.dto.UserFilter;
import com.abdev.spring.integregration.annotation.IT;
import com.abdev.spring.service.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
        System.out.println();
    }

    @Test
    void checkAuditing() {
        var user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "ov%", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
        System.out.println(users);
    }

    @Test
    void checkPageable() {
        var pageabale = PageRequest.of(1, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageabale);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));

        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkFirstTop() {

        var sortById = Sort.sort(User.class);
        var sort = sortById.by(User::getFirstname)
                .and(sortById.by(User::getLastname));

        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);

        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("%a%", "%ov%");
        Assertions.assertThat(users).hasSize(3);
        System.out.println(users );
    }

    @Test
    void checkUpdate() {
        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);
    }

}