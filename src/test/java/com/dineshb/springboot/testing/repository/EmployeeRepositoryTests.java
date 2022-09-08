package com.dineshb.springboot.testing.repository;

import com.dineshb.springboot.testing.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("Save Employee")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // Given..
        Employee employee = Employee.builder()
                .firstName("Dinesh")
                .lastName("Bhardwaj")
                .email("dinesh.bhardwaj@gmail.com")
                .build();

        // When..
        Employee savedEmployee = employeeRepository.save(employee);

        // Then..
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}
