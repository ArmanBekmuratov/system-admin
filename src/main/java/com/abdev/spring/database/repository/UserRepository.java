package com.abdev.spring.database.repository;

import com.abdev.spring.database.entity.Role;
import com.abdev.spring.database.entity.User;
import com.abdev.spring.database.pool.ConnectionPool;
import com.abdev.spring.dto.PersonalInfo;
import com.abdev.spring.dto.PersonalInfo2;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository {

    @Query(value = "select u from User u " +
            "where u.firstname like :firstname and u.lastname like :lastname")
    List<User> findAllBy(String firstname, String lastname);

    @Query( value = "SELECT u FROM users u WHERE u.username = :username",
            nativeQuery = true
    )
    List<User> findAllByUsername(String username);

    @Modifying
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)"  )
    int updateRole(Role role, Long...ids);

    Optional<User> findTopByOrderByIdDesc();

    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

//    @EntityGraph(value ="User.company")
    @EntityGraph(attributePaths = {"company"})
    @Query (value = "select u from User u",
    countQuery = "select count (distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

//    List<PersonalInfo> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);
 @Query(nativeQuery = true,
 value = "SELECT firstname, " +
         "lastname, " +
         "birth_date birthDate FROM users WHERE company_id = :companyId")
 List<PersonalInfo2> findAllByCompanyId(Integer companyId);
}
