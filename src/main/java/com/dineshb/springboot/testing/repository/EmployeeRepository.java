package com.dineshb.springboot.testing.repository;

import com.dineshb.springboot.testing.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom methods goes here..
    Optional<Employee> findByEmail(String email);
}
