package com.dineshb.springboot.testing.repository;

import com.dineshb.springboot.testing.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    @DisplayName("Save Employee")
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Dinesh")
                .lastName("Bhardwaj")
                .email("dinesh.bhardwaj@gmail.com")
                .build();

        // When - A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeRepository.save(employee);

        // Then - Verify the output of the operation performed.
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find all employees")
    public void givenEmployees_whenFindAll_thenReturnEmployeeList() {

        // Given -  A precondition (or) setup.
        Employee employee_1 = Employee.builder()
                .firstName("Dinesh")
                .lastName("Bhardwaj")
                .email("dinesh.bhardwaj@gmail.com")
                .build();

        Employee employee_2 = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();

        employeeRepository.save(employee_1);
        employeeRepository.save(employee_2);

        // When -  A behaviour or action that we are trying to perform.
        List<Employee> employeeList = employeeRepository.findAll();

        // Then - Verify the output of the operation performed.
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find employee by Id")
    public void givenEmployee_whenFindById_thenReturnEmployeeWithMatchingId() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Dinesh")
                .lastName("Bhardwaj")
                .email("dinesh.bhardwaj@gmail.com")
                .build();

        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee returnedEmployee = employeeRepository.findById(employee.getId()).get();

        // Then - Verify the output of the operation performed.
        assertThat(returnedEmployee).isNotNull();
    }

}
