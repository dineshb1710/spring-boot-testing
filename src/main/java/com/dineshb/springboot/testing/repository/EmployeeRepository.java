package com.dineshb.springboot.testing.repository;

import com.dineshb.springboot.testing.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom methods goes here..
    Optional<Employee> findByEmail(String email);

    // JPQL - [Java Persistence Query Language]

    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);
}
