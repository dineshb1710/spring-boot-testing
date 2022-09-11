package com.dineshb.springboot.testing.service;

import com.dineshb.springboot.testing.exception.EmployeeNotFoundException;
import com.dineshb.springboot.testing.exception.NonUniqueEmployeeException;
import com.dineshb.springboot.testing.model.Employee;
import com.dineshb.springboot.testing.repository.EmployeeRepository;
import com.dineshb.springboot.testing.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee_1;
    private Employee employee_2;

    @BeforeEach
    public void setup() {
        employee_1 = Employee.builder()
                .id(1L)
                .firstName("Janvi")
                .lastName("Bhardwaj")
                .email("janvi.bhardwaj@gmail.com")
                .build();

        employee_2 = Employee.builder()
                .id(2L)
                .firstName("Niharika Sharma")
                .lastName("Bhardwaj")
                .email("niharikasharma.bhardwaj@gmail.com")
                .build();
    }

    @Test
    @DisplayName("Save Employee - Positive Scenario")
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findByEmail(Mockito.anyString())).willReturn(Optional.empty());
        given(employeeRepository.save(Mockito.any(Employee.class))).willReturn(employee_1);

        // When -  A behaviour or action that we are trying to perform.
        Employee savedEmployee = employeeService.saveEmployee(employee_1);

        // Then - Verify the output of the operation performed.
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("Save Employee - Negative Scenario Non Unique Email")
    public void givenEmployeeObject_whenSaveEmployeeWithExitingEmail_thenThrowException() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(employee_1));

        // When -  A behaviour or action that we are trying to perform.
        org.junit.jupiter.api.Assertions.assertThrows(NonUniqueEmployeeException.class, () -> {
            employeeService.saveEmployee(employee_1);
        });

        // Then - Verify the output of the operation performed.
        Mockito.verify(employeeRepository, Mockito.never()).save(Mockito.any(Employee.class));
    }


    @Test
    @DisplayName("Find all Employees")
    public void givenEmployeeList_whenGetAllEmployees_thenReturnListOfEmployees() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findAll()).willReturn(List.of(employee_1, employee_2));

        // When -  A behaviour or action that we are trying to perform.
        List<Employee> employees = employeeService.findAllEmployees();

        // Then - Verify the output of the operation performed.
        assertThat(employees).isNotNull();
    }

    @Test
    @DisplayName("Find all employee Empty Scenario")
    public void givenNoEmployees_whenGetAllEmployees_thenReturnEmptyEmployeeList() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findAll()).willReturn(Collections.EMPTY_LIST);

        // When -  A behaviour or action that we are trying to perform.
        List<Employee> employees = employeeService.findAllEmployees();

        // Then - Verify the output of the operation performed.
        assertThat(employees.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Find Employee by Id - Employee Found")
    public void givenEmployeeId_whenEmployeeFoundById_thenReturnEmployeeObject() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findById(Mockito.anyLong())).willReturn(Optional.of(employee_1));

        // When -  A behaviour or action that we are trying to perform.
        Employee employee = employeeService.findById(1);

        // Then - Verify the output of the operation performed.
        assertThat(employee).isNotNull();
    }

    @Test
    @DisplayName("Find Employee by Id - EmployeeNotFoundException thrown")
    public void givenEmployeeId_whenEmployeeNotFoundById_thenThrowException() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.findById(Mockito.anyLong())).willReturn(Optional.empty());

        // When -  A behaviour or action that we are trying to perform.
        org.junit.jupiter.api.Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findById(1);
        });

        // Then - Verify the output of the operation performed.
        // 'EmployeeNotFoundException' is thrown is verified in above step.
    }


    @Test
    @DisplayName("Update Employee")
    public void givenEmployeeObject_whenUpdate_thenReturnUpdatedEmployeeObject() {

        // Given -  A precondition (or) setup.
        given(employeeRepository.save(Mockito.any(Employee.class))).willReturn(employee_1);
        employee_1.setFirstName("Janvi");
        employee_1.setLastName("Bhardwaj");

        // When -  A behaviour or action that we are trying to perform.
        Employee employee = employeeService.updateEmployee(employee_1);

        // Then - Verify the output of the operation performed.
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Janvi");
        assertThat(employee.getLastName()).isEqualTo("Bhardwaj");
    }

    @Test
    @DisplayName("Delete Employee by Id")
    public void givenEmployeeId_whenDelete_thenReturnNothing() {

        // Given -  A precondition (or) setup.
        willDoNothing().given(employeeRepository).deleteById(Mockito.anyLong());

        // When -  A behaviour or action that we are trying to perform.
        employeeService.deleteById(1L);

        // Then - Verify the output of the operation performed.
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}
