package com.dineshb.springboot.testing.repository;

import com.dineshb.springboot.testing.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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


    @Test
    @DisplayName("Find employee by email")
    public void givenEmployee_whenFindByEmail_thenReturnEmployeeMatchingEmail() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee returnedEmployee = employeeRepository.findByEmail(employee.getEmail()).get();

        // Then - Verify the output of the operation performed.
        assertThat(returnedEmployee).isNotNull();
    }


    @Test
    @DisplayName("Update Employee")
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Jani");
        savedEmployee.setLastName("Buaj");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // Then - Verify the output of the operation performed.
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Jani");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Buaj");
    }

    @Test
    @DisplayName("Delete Employee")
    public void givenEmployee_whenDeleted_thenReturnNull() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        employeeRepository.deleteById(employee.getId());

        // Then - Verify the output of the operation performed.
        Optional<Employee> returnedEmployee = employeeRepository.findById(employee.getId());
        assertThat(returnedEmployee.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Find Employee using JPQL with Index Params")
    public void givenFirstNameAndLastName_whenFindByJPQLIndexParams_thenReturnEmployeeObject() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeRepository.findByJPQLIndexParams(employee.getFirstName(), employee.getLastName());

        // Then - Verify the output of the operation performed.
        assertThat(savedEmployee).isNotNull();
    }


    @Test
    @DisplayName("Find Employee Using JPQL With Named Parameters")
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(employee.getFirstName(), employee.getLastName());

        // Then - Verify the output of the operation performed.
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Find Employee Using Native Query & Indexed Parameters")
    public void givenFirstNameAndLastName_whenFindByNativeQuery_thenReturnEmployeeObject() {

        // Given -  A precondition (or) setup.
        Employee employee = Employee.builder()
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();
        employeeRepository.save(employee);

        // When -  A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeRepository.findByNativeQueryIndexParams(employee.getFirstName(), employee.getLastName());

        // Then - Verify the output of the operation performed.
        assertThat(savedEmployee).isNotNull();
    }
}
