package com.abdev.spring.database.repository;

import com.abdev.spring.database.entity.Role;
import com.abdev.spring.database.entity.User;
import com.abdev.spring.dto.PersonalInfo;
import com.abdev.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
