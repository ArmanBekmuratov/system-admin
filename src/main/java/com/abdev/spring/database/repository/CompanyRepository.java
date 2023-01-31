package com.abdev.spring.database.repository;

import com.abdev.spring.database.entity.Company;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;



@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface CompanyRepository extends JpaRepository<Company, Integer> {

//     @Query(name = "Company.findByName")
     @Query(value = "select c from Company c " +
             "join fetch c.locales cl" +
             " where c.name = :name2")
     Optional<Company> findByName(String name2);

     List<Company> findAllByNameContainingIgnoreCase(String fragment);

}
